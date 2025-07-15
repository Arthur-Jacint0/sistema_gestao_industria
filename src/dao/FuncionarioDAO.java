package dao;

import config.ConexaoMySQL;
import model.Funcionario;
import model.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioDAO {
    private Connection conn = ConexaoMySQL.getConnection();
    private SetorDAO setorDAO = new SetorDAO();

    public ArrayList<Funcionario> listar(){
        try {
            ArrayList<Funcionario> funcionarios = new ArrayList<>();
            String sql = "SELECT * FROM funcionario;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Integer id = rs.getInt("id_funcionario");
                String nome = rs.getString("nome");
                String sobrenome = rs.getString("sobrenome");
                Integer idSetor = rs.getInt("id_setor");

                Setor setor = setorDAO.buscarPorId(idSetor);
                Funcionario funcionario = new Funcionario(id, nome, sobrenome, setor);

                funcionarios.add(funcionario);
            }
            return funcionarios;

        }catch (SQLException e){
            System.out.println("Deu ruim em listar os funcionarios. "+e.getMessage());
        }
        return null;
    }

    public Funcionario buscarPorId(Integer id){
        try {
            String sql = "SELECT*FROM funcionario WHERE id_funcionario = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Integer idFuncionario = rs.getInt("id_funcionario");
                String nome = rs.getString("nome");
                String descricao = rs.getString("sobrenome");
                Integer idSetor = rs.getInt("id_setor");

                Setor setor = setorDAO.buscarPorId(idSetor);
                Funcionario funcionario = new Funcionario(id, nome, descricao, setor);
                return funcionario;
            }
            return null;

        }catch (SQLException e){
            System.out.println("Erro ao buscar o funcionario pelo ID. " + e.getMessage());
        }

        return null;
    }

    public Boolean cadastrar(Funcionario funcionario){
        try {
            String sql = "INSERT INTO funcionario (nome, sobrenome, id_setor) VALUES(?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSobrenome());
            ps.setInt(3, funcionario.getSetor().getIdSetor());
            ps.executeUpdate();
            int qtdLinha = ps.executeUpdate();
            if (qtdLinha > 0){
                return true;
            }

        }catch (SQLException e){
            System.out.println("Erro ao cadastrar o novo funcionario. "+ e.getMessage());
        }
        return false;
    }

    public Boolean atualizar(Funcionario funcionario){
        try {
            String sql = "UPDATE funcionario SET nome = ?, sobrenome = ?, id_setor = ? WHERE id_funcionario = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getSobrenome());
            ps.setInt(3, funcionario.getSetor().getIdSetor());
            int qtdAtualizacoes = ps.executeUpdate();

            if (qtdAtualizacoes > 0){
                return true;
            }

        }catch (SQLException e){
            System.out.println("Erro ao atualizar o funcionario. " + e.getMessage());
        }
        return false;
    }

    public Boolean remover(Integer id){
        try {
            String sql = "DELETE FROM funcionario WHERE id_funcionario = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            Funcionario funcionarioRetornado = buscarPorId(id);
            if (funcionarioRetornado != null){
                ps.executeUpdate();
                return true;
            }

        }catch (SQLException e){
            System.out.println("Erro ao remover funcionario. " + e.getMessage());
        }
        return false;
    }

}

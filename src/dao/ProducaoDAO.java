package dao;

import config.ConexaoMySQL;
import model.Funcionario;
import model.Producao;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProducaoDAO {
    private Connection conn = ConexaoMySQL.getConnection();
    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public ArrayList<Producao> listar(){
        try {
            ArrayList<Producao> producaos = new ArrayList<>();
            String sql = "SELECT * FROM producao;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Integer idProducao = rs.getInt("id_producao");

                Integer idProduto = rs.getInt("id_produtos");
                Produto produto = produtoDAO.buscarPorId(idProduto);

                Integer idFuncionario = rs.getInt("id_funcionario");
                Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);

                String dataProducao = rs.getString("data_producao");
                Integer quantidade = rs.getInt("quantidade");

                Producao producao = new Producao(idProducao, produto, funcionario, dataProducao, quantidade);

                producaos.add(producao);
            }
            return producaos;

        }catch (SQLException e){
            System.out.println("Deu ruim em listar as produções. "+e.getMessage());
        }
        return null;
    }

    public Producao buscarPorId(Integer id){
        try {
            String sql = "SELECT*FROM producao WHERE id_producao = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Integer idProducao = rs.getInt("id_producao");
                Integer idProduto = rs.getInt("id_produtos");
                Integer idFuncionario = rs.getInt("id_funcionario");
                String dataProducao = rs.getString("data");
                Integer quantidade = rs.getInt("quantidade");

                Produto produto = produtoDAO.buscarPorId(idProduto);
                Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);

                Producao producao = new Producao(idProducao, produto, funcionario, dataProducao, quantidade);
                return producao;
            }
            return null;

        }catch (SQLException e){
            System.out.println("Erro ao buscar a produção pelo ID. " + e.getMessage());
        }

        return null;
    }
    public Boolean cadastrar(Producao producao) {
        try {
            String sql = "INSERT INTO producao (id_producao, id_produtos, id_funcionario, data_producao, quantidade) VALUES(?,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, producao.getIdProducao());
            ps.setInt(2, producao.getProduto().getIdProduto());
            ps.setInt(3, producao.getFuncionario().getIdFuncionario());
            ps.setString(4, producao.getDataProducao());
            ps.setInt(5, producao.getQuantidade());
            ps.executeUpdate();
            int qtdLinha = ps.executeUpdate();
            if (qtdLinha > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar o nova produção. " + e.getMessage());
        }
        return false;
    }
        public Boolean atualizar(Producao producao){
            try {
                String sql = "UPDATE producao SET id_produto = ?, id_funcionario = ?, data_producao = ?, quantidade = ? WHERE id_producao = ?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, producao.getIdProducao());
                ps.setInt(2, producao.getProduto().getIdProduto());
                ps.setInt(3, producao.getFuncionario().getIdFuncionario());
                ps.setString(4, producao.getDataProducao());
                ps.setInt(5, producao.getQuantidade());
                int qtdAtualizacoes = ps.executeUpdate();

                if (qtdAtualizacoes > 0){
                    return true;
                }

            }catch (SQLException e){
                System.out.println("Erro ao atualizar a producao. " + e.getMessage());
            }
            return false;
        }
    public Boolean remover(Integer id){
        try {
            String sql = "DELETE FROM producao WHERE id_producao = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            Producao producaoRetornado = buscarPorId(id);
            if (producaoRetornado != null){
                ps.executeUpdate();
                return true;
            }

        }catch (SQLException e){
            System.out.println("Erro ao remover produção. " + e.getMessage());
        }
        return false;
    }

}

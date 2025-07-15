package dao;

import config.ConexaoMySQL;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    private Connection conn = ConexaoMySQL.getConnection();

    public ArrayList<Produto> listar(){
        try {
            ArrayList<Produto> produtos = new ArrayList<>();
            String sql = "SELECT * FROM produtos;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Integer id = rs.getInt("id_produtos");
                String nome = rs.getString("nome_produto");
                String descricao = rs.getString("descricao");

                Produto produto = new Produto(id, nome, descricao);

                produtos.add(produto);
            }
            return produtos;

        }catch (SQLException e){
            System.out.println("Deu ruim em listar os produtos. "+e.getMessage());
        }
        return null;
    }

    public Produto buscarPorId(Integer id){
        try {
            String sql = "SELECT*FROM produtos WHERE id_produtos = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                Integer idProduto = rs.getInt("id_produtos");
                String nome = rs.getString("nome_produto");
                String descricao = rs.getString("descricao");
                Produto produto = new Produto(id, nome, descricao);
                return produto;
            }
            return null;

        }catch (SQLException e){
            System.out.println("Erro ao buscar o produto pelo ID. " + e.getMessage());
        }

        return null;
    }

    public Boolean cadastrar(Produto produto){
        try {
            String sql = "INSERT INTO produtos (nome_produto, descricao) VALUES(?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNomeProduto());
            ps.setString(2, produto.getDescricao());
            ps.executeUpdate();
            int qtdLinha = ps.executeUpdate();
            if (qtdLinha > 0){
                return true;
            }
            return false;

        }catch (SQLException e){
            System.out.println("Erro ao cadastrar o produto. "+ e.getMessage());
        }
        return false;
    }

    public Boolean atualizar(Produto produto){
        try {
            String sql = "UPDATE produtos SET nome_produto = ?, descricao = ? WHERE id_produtos = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNomeProduto());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getIdProduto());
            int qtdAtualizacoes = ps.executeUpdate();

            if (qtdAtualizacoes > 0){
                return true;
            }

        }catch (SQLException e){
            System.out.println("Erro ao atualizar o setor. " + e.getMessage());
        }
        return false;
    }

    public Boolean remover(Integer id){
        try {
            String sql = "DELETE FROM produtos WHERE id_produtos = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            Produto produtoRetornado = buscarPorId(id);
            if (produtoRetornado != null){
                ps.executeUpdate();
                return true;
            }

        }catch (SQLException e){
            System.out.println("Erro ao remover setor. " + e.getMessage());
        }
        return false;
    }
}

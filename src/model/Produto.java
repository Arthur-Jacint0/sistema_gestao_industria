package model;

public class Produto {
    private Integer idProduto;
    private String nomeProduto;
    private String descricao;

    public Produto() {
    }

    public Produto(Integer idProduto, String nomeProduto, String descricao) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.descricao = descricao;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "{"
                + "\"idProduto\":" + idProduto + ", "
                + "\"nomeProduto\":\"" + nomeProduto + "\", "
                + "\"descricao\":\"" + descricao + "\""
                + "}";
    }

    public void setId(int idProdutos) {
    }

    public void setNome(String nomeProduto) {
    }
}

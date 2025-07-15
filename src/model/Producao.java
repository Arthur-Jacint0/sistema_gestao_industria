package model;

import java.time.LocalDate;

public class Producao {
    private Integer idProducao;
    private Produto produto;
    private Funcionario funcionario;
    private String dataProducao;
    private Integer quantidade;
    private int id;

    public Producao() {
    }

    public Producao(Integer idProducao, Produto produto, Funcionario funcionario, String dataProducao, Integer quantidade) {
        this.idProducao = idProducao;
        this.produto = produto;
        this.funcionario = funcionario;
        this.dataProducao = dataProducao;
        this.quantidade = quantidade;
    }

    public Integer getIdProducao(){
        return idProducao;
    }

    public void setIdProducao(Integer idProducao) {
        this.idProducao = idProducao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(String dataProducao) {
        this.dataProducao = dataProducao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "{"
                + "\"idProducao\":" + idProducao + ", "
                + "\"produto\":" + produto.toString() + ", "
                + "\"funcionario\":" + funcionario.toString() + ", "
                + "\"dataProducao\":\"" + dataProducao + "\", "
                + "\"quantidade\":" + quantidade
                + "}";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setData(LocalDate dataProducao) {
        
    }
}

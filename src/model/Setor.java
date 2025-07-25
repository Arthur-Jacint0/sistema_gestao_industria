package model;

public class Setor {
    private Integer idSetor;
    private String nome;
    private String responsavel;
    private Setor setor;

    public Setor() {
    }

    public Setor(Integer idSetor, String nome, String responsavel) {
        this.idSetor = idSetor;
        this.nome = nome;
        this.responsavel = responsavel;
    }

    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "{"
                + "\"idSetor\":" + idSetor + ", "
                + "\"nome\":\"" + nome + "\", "
                + "\"responsavel\":\"" + responsavel + "\", "
                + "}";
    }
}

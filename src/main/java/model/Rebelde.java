package model;

public class Rebelde {
    private Long id;
    private String nome;
    private String genero;
    private Integer idade;
    private Boolean status;
    private Localizacao localizacao;
    private Inventario inventario;
    private Relatorio relatorio;

    public Rebelde() {

        this.status = true;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {

        this.inventario = inventario;
    }

    public Relatorio getRelatorio() {

        return relatorio;

    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }


}

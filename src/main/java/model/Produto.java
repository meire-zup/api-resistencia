package model;

import java.util.List;

public class Produto {
    private Long id;
    private String nome;
    private Double valor;

    private List<Inventario> inventarios;

    public Produto(Long id, String nome, double valor) {

        this.id = id;
        this.nome = nome;
        this.valor = valor;

    }

    public  Produto() {


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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", inventarios=" + inventarios +
                '}';
    }
}

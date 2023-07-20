package model;

import java.util.List;

public class Delator {

    private Long id;
    private String nome;
    private List<Rebelde> rebeldes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Rebelde> getRebeldes() {
        return rebeldes;
    }

    public void setRebeldes(List<Rebelde> rebeldes) {
        this.rebeldes = rebeldes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

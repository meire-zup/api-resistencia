package model;

import java.util.List;

public class Inventario {
    private Long id;
    private Rebelde rebelde;
    private List<Produto> recursos;

    public Inventario(Rebelde rebelde, List<Produto> recursos) {
        this.rebelde = rebelde;
        this.recursos = recursos;
    }

    public Inventario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rebelde getRebelde() {
        return rebelde;
    }

    public void setRebelde(Rebelde rebelde) {
        this.rebelde = rebelde;
    }

    public List<Produto> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Produto> recursos) {
        this.recursos = recursos;
    }
}

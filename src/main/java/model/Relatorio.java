package model;

import java.util.List;

public class Relatorio {
    private Long id;
    private int quantidadeRelatorio;
    private Rebelde rebelde;

    public Relatorio() {

        this.quantidadeRelatorio = 0;

    }

    public Rebelde getRebelde() {

        return rebelde;
    }

    public int getQuantidadeRelatorio() {
        return quantidadeRelatorio;
    }

    public void incrementarQuantidadeRelatorio() {
        quantidadeRelatorio++;
        if (quantidadeRelatorio >= 3) {
            rebelde.setStatus(false);
        }
    }

    public void setRebelde(Rebelde rebelde) {
        this.rebelde = rebelde;
    }

    public void setQuantidadeRelatorio(int quantidadeRelatorios) {
        this.quantidadeRelatorio = quantidadeRelatorios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

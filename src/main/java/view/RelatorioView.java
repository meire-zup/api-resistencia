package view;


import model.Relatorio;
import service.RelatorioService;

public class RelatorioView {

    private RelatorioService relatorioService;


    public RelatorioView(RelatorioService relatorioService) {

        this.relatorioService = relatorioService;

    }

    public void relatarTraidor(String nomeRebelde) {

        relatorioService.relatarTraidor(nomeRebelde);

    }
}

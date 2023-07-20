package view;

import model.Relatorio;
import service.RelatorioService;

import java.util.Scanner;

public class RelatorioView {

    private RelatorioService relatorioService;
    private Scanner scanner;


    public RelatorioView(RelatorioService relatorioService, Scanner scanner) {

        this.relatorioService = relatorioService;
        this.scanner = scanner;

    }

    public void relatarTraidor() {

        System.out.println("Informe seu nome:");
        String nomeDelator = scanner.nextLine();
        System.out.println("Informe o nome do rebelde que gostaria de denunciar:");
        String nomeRebelde = scanner.nextLine();

        relatorioService.relatarTraidor(nomeDelator, nomeRebelde);

    }

    public void obterPorcentagemDeRebeldes() {

        relatorioService.obterPorcentagemDeRebeldes();

    }

    public void obterPorcentagemDeTraidores() {

        relatorioService.obterPorcentagemDeTraidores();

    }

}

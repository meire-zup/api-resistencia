package service;

import dao.DelatorDAO;
import dao.RelatorioDAO;

public class RelatorioService {

    RelatorioDAO relatorioDAO;
    DelatorDAO delatorDAO;


    public RelatorioService(RelatorioDAO relatorioDAO, DelatorDAO delatorDAO) {

        this.relatorioDAO = relatorioDAO;
        this.delatorDAO = delatorDAO;

    }

    public void relatarTraidor(String nomeDelator, String nomeRebelde) {

        if(delatorDAO.existeDenuncia(nomeDelator, nomeRebelde)) {

            System.out.println("Delator não pode denunciar o mesmo rebelde mais que uma vez!");

        } else {

            relatorioDAO.denunciarTraidor(nomeRebelde);

        }
    }
    // Método calcula porcentagem de rebeldes
    public void obterPorcentagemDeRebeldes() {

        Double porcentagemRebeldes = relatorioDAO.obterPorcentagemRebeldes();

        System.out.printf("A porcentagem de rebeldes é de %.2f%%\n", porcentagemRebeldes);

    }

    // Método calcula porcentagem de traidores
    public void obterPorcentagemDeTraidores() {

        Double porcentagemTraidores = relatorioDAO.obterPorcentagemTraidores();

        System.out.printf("A porcentagem de rebeldes é de %.2f%%\n", porcentagemTraidores);
    }
}

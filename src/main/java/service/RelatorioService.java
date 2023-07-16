package service;

import dao.RebeldeDAO;
import model.Rebelde;
import model.Relatorio;

import java.util.ArrayList;
import java.util.List;

public class RelatorioService {

    private Relatorio relatorio;
    private RebeldeDAO rebeldeDAO;

    private List<Rebelde> traidores;


    public RelatorioService(Relatorio relatorio, RebeldeDAO rebeldeDAO) {

        this.relatorio = relatorio;
        this.rebeldeDAO = rebeldeDAO;
        this.traidores = new ArrayList<>();

    }

//criar uma lista de traidores se estiver na lista é porque já tem relatorio, se naõ criar relatorio
    public void relatarTraidor (String nomeRebelde) {

        Rebelde rebeldeEncontrado = rebeldeDAO.buscarRebeldePorNome(nomeRebelde);
        if (traidores.contains(rebeldeEncontrado)) {

            relatorio.incrementarQuantidadeRelatorios();
            relatorio.getRebelde().setNome(nomeRebelde);


        } else {

            Relatorio relatorio = new Relatorio(rebeldeEncontrado);
            traidores.add(rebeldeEncontrado);
            relatorio.incrementarQuantidadeRelatorios();
            relatorio.getRebelde().setNome(nomeRebelde);

        }
    }


    public Relatorio getRelatorio() {
        return relatorio;
    }

    public void setRelatorio(Relatorio relatorio) {
        this.relatorio = relatorio;
    }

    public List<Rebelde> getTraidores() {
        return traidores;
    }

    public void setTraidores(List<Rebelde> traidores) {
        this.traidores = traidores;
    }
}

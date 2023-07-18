/*package service;

import dao.RebeldeDAO;
import model.Rebelde;
import model.Relatorio;

import java.util.ArrayList;
import java.util.List;

public class RelatorioService {

    private Relatorio relatorio = new Relatorio();
    private RebeldeDAO rebeldeDAO;

    private List<Rebelde> traidores;


    public RelatorioService(RebeldeDAO rebeldeDAO) {


        this.rebeldeDAO = rebeldeDAO;
        this.traidores = new ArrayList<>();

    }

//criar uma lista de traidores se estiver na lista é porque já tem relatorio, se naõ criar relatorio
    public void relatarTraidor (String nomeRebelde) {

        Rebelde rebeldeEncontrado = rebeldeDAO.buscarRebeldePorNome(nomeRebelde);
        if (traidores.contains(rebeldeEncontrado)) {

            relatorio.incrementarQuantidadeRelatorios();
            relatorio.getRebelde().setNome(rebeldeEncontrado.getNome());
            //relatorio.setRebelde(rebeldeEncontrado);


        } else {

            Relatorio relatorio = new Relatorio(rebeldeEncontrado);
            traidores.add(rebeldeEncontrado);
            relatorio.incrementarQuantidadeRelatorios();
            relatorio.getRebelde().setNome(rebeldeEncontrado.getNome());
            //relatorio.setRebelde(rebeldeEncontrado);

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
*/
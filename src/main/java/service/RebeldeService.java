package service;

import dao.RebeldeDAO;
import model.Rebelde;

import java.sql.SQLException;

public class RebeldeService {

    RebeldeDAO rebeldeDAO;

    public RebeldeService(RebeldeDAO rebeldeDAO) {
        this.rebeldeDAO = rebeldeDAO;
    }

    // Método verifica se um rebelde já existe no banco de dados e se não existe adiciona
    public Rebelde adicionar (String nome, String genero, Integer idade, String ip) {

        Rebelde rebelde = null;

        if (rebeldeDAO.verificarSeRebeldeExiste(nome)) {

            System.out.println("Rebelde " + nome + " já existe!");

        } else {

            rebelde = rebeldeDAO.adicionarRebelde(nome, genero, idade, ip);

        }

        return rebelde;

    }
        // Método atualiza nova localização do rebelde
        public void adicionarNovaLocalizacao (String ip, String nome) {

        if(rebeldeDAO.verificarSeRebeldeExiste(nome)){

            rebeldeDAO.atualizarLocalizacaoRebelde(ip, nome);

        } else {

            System.out.println("Rebelde " + nome + " não existe!");
        }

    }


}

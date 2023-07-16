package org.example;

import dao.ConexaoDAO;
import dao.LocalizacaoDAO;
import dao.RebeldeDAO;
import model.Rebelde;

public class Main {
    public static void main(String[] args) {

        ConexaoDAO conexao = new ConexaoDAO();
        conexao.getConexao();

        LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO(conexao);
        RebeldeDAO rebeldeDAO = new RebeldeDAO(conexao, localizacaoDAO);

        System.out.println(rebeldeDAO.buscarRebeldePorNome("Thor"));
        Rebelde rebelde = rebeldeDAO.buscarRebeldePorNome("Thor");
        System.out.println(rebelde.getNome());
    }


}
package org.example;

import dao.ConexaoDAO;
import dao.LocalizacaoDAO;
import dao.ProdutoDAO;
import dao.RebeldeDAO;
import model.Localizacao;
import model.Produto;
import model.Rebelde;

public class Main {
    public static void main(String[] args) {

        ConexaoDAO conexao = new ConexaoDAO();
        conexao.getConexao();

        LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO(conexao);
        RebeldeDAO rebeldeDAO = new RebeldeDAO(conexao, localizacaoDAO);
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);

        /*System.out.println(rebeldeDAO.buscarRebeldePorNome("Thor"));
        Rebelde rebelde = rebeldeDAO.buscarRebeldePorNome("Thor");
        //System.out.println(localizacaoDAO.buscarLocalizacaoPorIp("192.168.1.1"));
        System.out.println(rebelde.getNome());
        Localizacao localizacao = localizacaoDAO.buscarLocalizacaoPorIp("192.168.1.1");
        System.out.println(localizacao.getId());
        //System.out.println(rebelde.getLocalizacao());*/
        //rebeldeDAO.adicionarRebelde("Volverini","Masculino",35);
        Produto produto = produtoDAO.buscarProdutoPorNome("Arma");
        System.out.println(produto.getNome());
    }


}
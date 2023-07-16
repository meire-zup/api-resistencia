package org.example;

import dao.*;
import model.*;
import service.RelatorioService;
import view.InventarioView;
import view.RebeldeView;
import view.RelatorioView;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ConexaoDAO conexao = new ConexaoDAO();
        conexao.getConexao();

        Scanner scanner = new Scanner(System.in);

        LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO(conexao);
        RebeldeDAO rebeldeDAO = new RebeldeDAO(conexao, localizacaoDAO);
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        InventarioDAO inventarioDAO = new InventarioDAO(conexao);
        RebeldeView rebeldeView = new RebeldeView(rebeldeDAO, scanner);
        InventarioView inventarioView = new InventarioView(inventarioDAO, scanner, rebeldeDAO);

        /*System.out.println(rebeldeDAO.buscarRebeldePorNome("Thor"));
        Rebelde rebelde = rebeldeDAO.buscarRebeldePorNome("Thor");
        //System.out.println(localizacaoDAO.buscarLocalizacaoPorIp("192.168.1.1"));
        System.out.println(rebelde.getNome());
        Localizacao localizacao = localizacaoDAO.buscarLocalizacaoPorIp("192.168.1.1");
        System.out.println(localizacao.getId());
        //System.out.println(rebelde.getLocalizacao());*/
        //rebeldeDAO.adicionarRebelde("Volverini","Masculino",35);
        //Produto produto = produtoDAO.buscarProdutoPorNome("Arma");
        //System.out.println(produto.getNome());
        //System.out.println(inventarioDAO.buscarInventarioPorId(9L));
        Inventario inventario = new Inventario();
         inventario =       inventarioDAO.buscarInventarioPorId(9L);
        //System.out.println(inventario.getRebelde().getNome());
        Relatorio relatorio = new Relatorio();

        RelatorioService relatorioService = new RelatorioService(relatorio, rebeldeDAO);
        RelatorioView relatorioView = new RelatorioView(relatorioService);
        relatorioView.relatarTraidor("Thor");
        System.out.println(relatorio.getRebelde().getNome());
        System.out.println(relatorioService.getTraidores().size());

    }


}
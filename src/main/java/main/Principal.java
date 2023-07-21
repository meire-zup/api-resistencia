package main;

import dao.*;
import service.InventarioService;
import service.RebeldeService;
import service.RelatorioService;
import view.InventarioView;
import view.MenuView;
import view.RebeldeView;
import view.RelatorioView;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        ConexaoDAO conexao = new ConexaoDAO();
        conexao.getConexao();

        Scanner scanner = new Scanner(System.in);

        LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO(conexao);
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        InventarioDAO inventarioDAO = new InventarioDAO(conexao, produtoDAO);
        RelatorioDAO relatorioDAO = new RelatorioDAO(conexao);
        RebeldeDAO rebeldeDAO = new RebeldeDAO(conexao, localizacaoDAO,inventarioDAO, relatorioDAO);
        DelatorDAO delatorDAO = new DelatorDAO(conexao, rebeldeDAO);

        RebeldeService rebeldeService = new RebeldeService(rebeldeDAO);
        InventarioService inventarioService = new InventarioService(inventarioDAO, produtoDAO, rebeldeDAO);
        RelatorioService relatorioService = new RelatorioService(relatorioDAO, delatorDAO);

        RebeldeView rebeldeView = new RebeldeView(rebeldeService, scanner);
        InventarioView inventarioView = new InventarioView(scanner, inventarioService);
        RelatorioView relatorioView = new RelatorioView(relatorioService, scanner);
        MenuView menuView = new MenuView(rebeldeView, inventarioView, relatorioView, scanner);

        menuView.iniciarPrograma();


    }
}

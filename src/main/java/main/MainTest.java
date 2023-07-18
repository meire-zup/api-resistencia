package main;

import dao.*;
import model.Produto;
import view.InventarioView;
import view.RebeldeView;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {

        ConexaoDAO conexao = new ConexaoDAO();
        conexao.getConexao();

        Scanner scanner = new Scanner(System.in);

        LocalizacaoDAO localizacaoDAO = new LocalizacaoDAO(conexao);
        ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
        InventarioDAO inventarioDAO = new InventarioDAO(conexao, produtoDAO);
        RebeldeDAO rebeldeDAO = new RebeldeDAO(conexao, localizacaoDAO,inventarioDAO);

        RebeldeView rebeldeView = new RebeldeView(rebeldeDAO, scanner);
        InventarioView inventarioView = new InventarioView(inventarioDAO, scanner, rebeldeDAO);

        /*System.out.println(rebeldeDAO.buscarRebeldePorNome("Thor"));
        Rebelde rebelde = rebeldeDAO.buscarRebeldePorNome("Thor");
        //System.out.println(localizacaoDAO.buscarLocalizacaoPorIp("192.168.1.1"));
        System.out.println(rebelde.getNome());
        Localizacao localizacao = localizacaoDAO.buscarLocalizacaoPorIp("192.168.1.1");
        System.out.println(localizacao.getId());
        //System.out.println(rebelde.getLocalizacao());*/
        //rebeldeDAO.adicionarRebelde("Rebelde Teste","Masculino",35);
        Produto produto = produtoDAO.buscarProdutoPorNome("Arma");
        System.out.println(produto.getNome());
        //System.out.println(inventarioDAO.buscarInventarioPorId(9L));

        //Inventario inventario = new Inventario();
        // inventario =       inventarioDAO.buscarInventarioPorId(9L);
        //System.out.println(inventario.getRebelde().getNome());
        //Relatorio relatorio = new Relatorio();
       // RelatorioService relatorioService = new RelatorioService(rebeldeDAO);
       // RelatorioView relatorioView = new RelatorioView(relatorioService, relatorio);

        //relatorioView.relatarTraidor("Thor");
        //System.out.println(relatorio.getRebelde().getNome());
        //System.out.println(relatorioService.getTraidores().size());
        //System.out.println(relatorio.getQuantidadeRelatorios());
        //Rebelde rebelde = rebeldeDAO.buscarRebeldePorNome("Thor");
        //System.out.println(rebelde.getNome());

        //relatorioService.relatarTraidor("Thor");
        //System.out.println( relatorioService.getRelatorio().getRebelde().getNome());
        //System.out.println(relatorioService.getRelatorio().getQuantidadeRelatorios());
       //localizacaoDAO.adicionarLocalizacao("192.168.1.6");
      // Localizacao localizacao = localizacaoDAO.adicionarLocalizacao("192.168.1.7");
     // System.out.println(localizacao.getId());
     // System.out.println(localizacao.getIp());
        //TesteDAO testeDAO = new TesteDAO(conexao);
        //System.out.println(testeDAO.buscaIdDaLocalizacao("192.168.1.4"));
        //testeDAO.adicionarLocalizacaoParaRebelde();

       //System.out.println(rebeldeDAO.buscaIdDoRebelde("Meire"));
        //System.out.println(localizacaoDAO.buscaIdDaLocalizacao("192.168.1.4"));
        //System.out.println();
       // System.out.println(rebeldeDAO.verificarSeRebeldeExiste("Thor"));
        //inventarioDAO.criarInventario();
       //Rebelde rebeldeJucemeire = rebeldeDAO.adicionarRebelde("Jucemeire", "feminino", 47, "192.168.1.4");
        //System.out.println(rebeldeJucemeire.getLocalizacao().getId());
        //rebeldeDAO.atualizarLocalizacaoRebelde("192.168.1.4", "Thor");
       //System.out.println(inventarioDAO.criarInventario2().getId());
        //System.out.println(rebeldeDAO.adicionarRebelde("Jucemeire", "feminino", 47, "192.168.1.4").getNome());
       // produtoDAO.criarProduto("teste", 300.0);
        //inventarioDAO.adicionarProdutoNoInventario2("Jucemeire", "Arma");
        //System.out.println(inventarioDAO.buscarIdInventarioPorNomeRebelde("Thor"));
        //Localizacao localizacao = localizacaoDAO.buscarLocalizacaoPorIp("192.168.1.1");
        //System.out.println(localizacao.getId());


    }


}
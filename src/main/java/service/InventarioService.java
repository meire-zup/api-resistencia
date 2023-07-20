package service;

import dao.InventarioDAO;
import dao.ProdutoDAO;
import dao.RebeldeDAO;

public class InventarioService {

    InventarioDAO inventarioDAO;

    ProdutoDAO produtoDAO;
    RebeldeDAO rebeldeDAO;

    public InventarioService(InventarioDAO inventarioDAO, ProdutoDAO produtoDAO, RebeldeDAO rebeldeDAO) {

        this.inventarioDAO = inventarioDAO;
        this.produtoDAO = produtoDAO;
        this.rebeldeDAO = rebeldeDAO;

    }
        // Método adiciona recurso ao inventário do rebelde
        // Fazer lógica para não ser adicionado duas vezes
        // Fazer lógica para traidor não poder adquirir produto
    public void adicionaProdutoNoInventarioDoRebelde(String nome, String produto) {

        if (rebeldeDAO.verificarSeRebeldeExiste(nome) == false) {

            System.out.println("Rebelde " + nome + " não existe!");


        } else if (produtoDAO.verificarSeProdutoExiste(produto) == false) {

            System.out.println("Produto " + produto + " não existe.");

        } else {

            inventarioDAO.adicionarProdutoNoInventario(nome, produto);

        }

    }
}

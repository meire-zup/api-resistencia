package view;

import dao.InventarioDAO;
import dao.RebeldeDAO;
import model.Rebelde;

import java.util.ArrayList;
import java.util.Scanner;

public class InventarioView {
    private InventarioDAO inventarioDAO;
    private Scanner scanner;


    private RebeldeDAO rebeldeDAO;

    public InventarioView(InventarioDAO inventarioDAO, Scanner scanner, RebeldeDAO rebeldeDAO) {

        this.inventarioDAO = inventarioDAO;
        this.scanner = scanner;
        this.rebeldeDAO = rebeldeDAO;

    }
    // Método cria um inventário para um rebelde
    public void posseRebelde() {

        System.out.println("Informe o nome do rebelde:");
        Rebelde rebelde = rebeldeDAO.buscarRebeldePorNome(scanner.nextLine());

        inventarioDAO.criarInventario(rebelde, new ArrayList<>());

    }
}

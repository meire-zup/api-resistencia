package view;


import service.InventarioService;
import java.util.Scanner;

public class InventarioView {
    private Scanner scanner;

    InventarioService inventarioService;

    public InventarioView(Scanner scanner, InventarioService inventarioService) {

        this.scanner = scanner;
        this.inventarioService = inventarioService;
    }

    // Método compra recursos
    public void adicionaProduto() {

        System.out.println("Informe o nome:");
        String nome = scanner.nextLine();

        System.out.println("Informe o produto que deseja adicionar:");
        String produto = scanner.nextLine();

        inventarioService.adicionaProdutoNoInventarioDoRebelde(nome, produto);

    }

}

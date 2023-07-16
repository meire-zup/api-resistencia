package view;

import dao.RebeldeDAO;

import java.util.Scanner;

public class RebeldeView {

    private RebeldeDAO rebeldeDAO;

    private Scanner scanner;

    public RebeldeView(RebeldeDAO rebeldeDAO, Scanner scanner) {

        this.rebeldeDAO = rebeldeDAO;
        this.scanner = scanner;

    }

    // Método que interage com o usuário para adicionar rebelde
    public void adicionarRebelde() {

        System.out.println("Informe o nome:");
        String nome = scanner.nextLine();

        System.out.println("Informe o gênero:");
        String genero = scanner.nextLine();

        System.out.println("Informe a idade:");
        Integer idade = scanner.nextInt();

        rebeldeDAO.adicionarRebelde(nome, genero, idade);

    }

}

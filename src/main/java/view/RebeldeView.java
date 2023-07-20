package view;

import dao.RebeldeDAO;
import service.RebeldeService;

import java.util.Scanner;

public class RebeldeView {

    private RebeldeService rebeldeService;

    private Scanner scanner;

    public RebeldeView(RebeldeService rebeldeService, Scanner scanner) {

        this.rebeldeService = rebeldeService;
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

        scanner.nextLine();

        System.out.println("Informe o ip da localização:");
        String ip = scanner.nextLine();

        rebeldeService.adicionar(nome,genero,idade,ip);

    }

    // Método atualiza localização do rebelde
    public void atualizaLocalizacao () {

        System.out.println("Informe o nome:");
        String nome = scanner.nextLine();

        System.out.println("Informe o ip da localização:");
        String ip = scanner.nextLine();

        rebeldeService.adicionarNovaLocalizacao(ip, nome);
    }

}

package view;

import java.util.HashMap;
import java.util.Scanner;

public class MenuView {

    RebeldeView rebeldeView;
    InventarioView inventarioView;
    RelatorioView relatorioView;
    Scanner scanner;

    public MenuView(RebeldeView rebeldeView, InventarioView inventarioView,
                    RelatorioView relatorioView, Scanner scanner) {
        this.rebeldeView = rebeldeView;
        this.inventarioView = inventarioView;
        this.relatorioView = relatorioView;
        this.scanner = scanner;
    }

    public void iniciarPrograma() {
        int escolha = 0;

        do {

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar rebelde para a luta");
            System.out.println("2 - Atualizar nova localização");
            System.out.println("3 - Adquirir produto");
            System.out.println("4 - Exibir porcentagem de rebeldes");
            System.out.println("5 - Exibir porcentagem de traidores");
            System.out.println("6 - Delatar traidor");
            System.out.println("7 - Exibir recursos de rebelde");
            System.out.println("8 - Sair");
            System.out.print("Escolha: ");
            escolha = scanner.nextInt();
            acionaMetodo(escolha);

        } while (escolha < 1 || escolha > 8);

        outraConsulta();


    }

    public void acionaMetodo(int escolha) {

        switch (escolha) {

            case 1:

                rebeldeView.adicionarRebelde();
                break;

            case 2:

                rebeldeView.atualizaLocalizacao();
                break;

            case 3:

                inventarioView.adicionaProduto();
                break;

            case 4:

                relatorioView.obterPorcentagemDeRebeldes();
                break;

            case 5:

                relatorioView.obterPorcentagemDeTraidores();
                break;

            case 6:

                relatorioView.relatarTraidor();
                break;

            case 7:

                inventarioView.obterRecursosRebelde();
                break;

            case 8:
                System.out.println("Fim!");
                System.exit(0);

        }

    }

    public void outraConsulta() {

        int escolha;

        do {

            System.out.println("Deseja realizar outra operação?");
            System.out.println("Digite 1 para sim ou 2 para não");
            escolha = scanner.nextInt();
            if (escolha != 1 && escolha != 2) {

                System.out.println("Opção inválida!");

            } else if (escolha == 1) {

                iniciarPrograma();

            } else if (escolha == 2) {
                System.out.println("Fim!");
                System.exit(0);

            }

        } while (escolha != 1 && escolha != 2);

    }

}

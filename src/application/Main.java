// Lays Cristiane Varela Brasilino
// turma: 02 ADS
package application;

import entities.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner sc = new Scanner(System.in);
        int escolha = 0;

        // Livros de programação pré-cadastrados
        biblioteca.adicionarLivros("Clean Code", "Robert C. Martin", 2008);
        biblioteca.adicionarLivros("Código Limpo", "Robert C. Martin", 2009);
        biblioteca.adicionarLivros("Estruturas de Dados e Algoritmos em Java", "Robert Lafore", 2002);
        biblioteca.adicionarLivros("Padrões de Projeto", "Erich Gamma", 1994);
        biblioteca.adicionarLivros("Entendendo Algoritmos", "Aditya Bhargava", 2016);
        biblioteca.adicionarLivros("Java: Como Programar", "Deitel & Deitel", 2014);
        biblioteca.adicionarLivros("Refatoração", "Martin Fowler", 1999);
        biblioteca.adicionarLivros("Arquitetura Limpa", "Robert C. Martin", 2017);
        biblioteca.adicionarLivros("The Pragmatic Programmer", "Andrew Hunt e David Thomas", 1999);
        biblioteca.adicionarLivros("Design Patterns em Java", "Steven John Metsker", 2002);

        // Recomendações com pesos
        biblioteca.recomendarLivroComPeso("Clean Code", "Código Limpo", 1);
        biblioteca.recomendarLivroComPeso("Clean Code", "Arquitetura Limpa", 2);
        biblioteca.recomendarLivroComPeso("Código Limpo", "Refatoração", 1);
        biblioteca.recomendarLivroComPeso("Java: Como Programar", "Estruturas de Dados e Algoritmos em Java", 2);
        biblioteca.recomendarLivroComPeso("Estruturas de Dados e Algoritmos em Java", "Entendendo Algoritmos", 1);
        biblioteca.recomendarLivroComPeso("Refatoração", "Padrões de Projeto", 2);
        biblioteca.recomendarLivroComPeso("Padrões de Projeto", "Design Patterns em Java", 1);
        biblioteca.recomendarLivroComPeso("Arquitetura Limpa", "The Pragmatic Programmer", 3);

        while (true) {
            System.out.println("\n### GERENCIAMENTO DE LIVROS DA BIBLIOTECA ###");
            System.out.println("[1] ADICIONAR LIVRO");
            System.out.println("[2] REMOVER LIVRO");
            System.out.println("[3] LISTAR LIVROS");
            System.out.println("[4] BUSCAR LIVRO");
            System.out.println("[5] ADICIONAR À FILA DE ESPERA");
            System.out.println("[6] LIBERAR LIVRO PARA PRÓXIMO DA FILA");
            System.out.println("[7] REGISTRAR CONSULTA");
            System.out.println("[8] VER HISTÓRICO DO USUÁRIO");
            System.out.println("[9] ADICIONAR RECOMENDAÇÃO");
            System.out.println("[10] MOSTRAR RECOMENDAÇÃO");
            System.out.println("[11] SAIR");
            System.out.println("[12] VER CAMINHOS MAIS PRÓXIMOS ENTRE LIVROS (DIJKSTRA)\n");
            System.out.print("ESCOLHA UMA OPÇÃO: ");

            try {
                escolha = sc.nextInt();
            } catch (Exception e) {
                System.out.println("DIGITE UM NÚMERO INTEIRO");
                sc.nextLine();
                continue;
            }

            System.out.println();
            sc.nextLine(); // Consumir quebra de linha do nextInt

            switch (escolha) {
                case 1:
                    try {
                        System.out.print("QUAL O TÍTULO DO LIVRO: ");
                        String titulo = sc.nextLine();
                        System.out.print("QUAL O AUTOR DO LIVRO: ");
                        String autor = sc.nextLine();
                        System.out.print("QUAL O ANO DE PUBLICAÇÃO DO LIVRO: ");
                        int ano = sc.nextInt();
                        sc.nextLine();
                        biblioteca.adicionarLivros(titulo, autor, ano);
                    } catch (InputMismatchException e) {
                        System.out.println("ANO INVÁLIDO! DIGITE UM NÚMERO.");
                        sc.nextLine();
                    } catch (Exception e) {
                        System.out.println("OCORREU UM ERRO. TENTE NOVAMENTE");
                        sc.nextLine();
                    }
                    break;

                case 2:
                    System.out.print("QUAL O TÍTULO DO LIVRO QUE VAMOS REMOVER: ");
                    String tituloParaRemover = sc.nextLine();
                    biblioteca.removerLivro(tituloParaRemover);
                    break;

                case 3:
                    biblioteca.listarLivros();
                    break;

                case 4:
                    System.out.print("DIGITE O TÍTULO DO LIVRO PARA BUSCARMOS: ");
                    String tituloParaBuscar = sc.nextLine();
                    Livro livroAchado = biblioteca.procurarlivros(tituloParaBuscar);
                    if (livroAchado != null) {
                        System.out.printf("%s ENCONTRADO\n", tituloParaBuscar);
                    } else {
                        System.out.println("LIVRO NÃO ENCONTRADO");
                    }
                    break;

                case 5:
                    System.out.print("DIGITE O TÍTULO DO LIVRO QUE VOCÊ ESTA ESPERANDO: ");
                    String tituloFila = sc.nextLine();
                    System.out.print("DIGITE SEU NOME: ");
                    String usuarioFila = sc.nextLine();
                    biblioteca.adicionarListaEspera(tituloFila, usuarioFila);
                    System.out.printf("%s - VOCÊ FOI ADICIONADO À FILA DE ESPERA.\n", usuarioFila);
                    break;

                case 6:
                    System.out.print("DIGITE O TÍTULO DO LIVRO QUE SERÁ LIBERADO: ");
                    String tituloLiberado = sc.nextLine();
                    biblioteca.liberarLivro(tituloLiberado);
                    break;

                case 7:
                    System.out.print("DIGITE SEU NOME: ");
                    String usuarioConsulta = sc.nextLine();
                    System.out.print("DIGITE O TÍTULO DO LIVRO QUE VAMOS CONSULTAR: ");
                    String livroConsulta = sc.nextLine();
                    biblioteca.registrarConsulta(usuarioConsulta, livroConsulta);
                    break;

                case 8:
                    System.out.print("DIGITE SEU NOME PARA VER O HISTÓRICO: ");
                    String usuarioHistorico = sc.nextLine();
                    biblioteca.exibirHistorico(usuarioHistorico);
                    break;

                case 9:
                    System.out.print("DIGITE O TÍTULO DO LIVRO BASE: ");
                    String tituloBase = sc.nextLine();
                    System.out.print("DIGITE O TÍTULO DA PRIMEIRA RECOMENDAÇÃO: ");
                    String primeiraRecomendacao = sc.nextLine();
                    System.out.print("DIGITE O TÍTULO DA SEGUNDA RECOMENDAÇÃO: ");
                    String segundaRecomendacao = sc.nextLine();
                    System.out.print("PESO ENTRE OS LIVROS: ");
                    int peso = sc.nextInt();
                    sc.nextLine();
                    boolean sucesso1 = biblioteca.recomendarLivroComPeso(tituloBase, primeiraRecomendacao, peso);
                    boolean sucesso2 = biblioteca.recomendarLivroComPeso(tituloBase, segundaRecomendacao, peso);
                    if (sucesso1 && sucesso2) {
                        System.out.println("AS DUAS RECOMENDAÇÕES FORAM ADICIONADAS COM SUCESSO!");
                    } else {
                        System.out.println("ERRO AO ADICIONAR UMA OU AMBAS AS RECOMENDAÇÕES.");
                    }
                    break;

                case 10:
                    biblioteca.exibirRecomendacoes();
                    break;

                case 11:
                    System.out.println("SAINDO... OBRIGADO PELA ATENÇÃO!");
                    sc.close();
                    return;

                case 12:
                    System.out.print("DIGITE O TÍTULO DO LIVRO DE ORIGEM: ");
                    String origemTitulo = sc.nextLine();
                    Livro origemLivro = biblioteca.procurarlivros(origemTitulo);
                    if (origemLivro == null) {
                        System.out.println("LIVRO DE ORIGEM NÃO ENCONTRADO.");
                        break;
                    }
                    Map<Livro, Integer> distancias = biblioteca.getGrafo().dijkstra(origemLivro);
                    System.out.println("CAMINHOS MAIS PRÓXIMOS A PARTIR DE \"" + origemTitulo + "\":");
                    for (Map.Entry<Livro, Integer> entry : distancias.entrySet()) {
                        if (!entry.getKey().equals(origemLivro)) {
                            System.out.println("- " + entry.getKey().getnomeLivro() + " (distância: " + entry.getValue() + ")");
                        }
                    }
                    break;

                default:
                    System.out.println("### OPÇÃO INVÁLIDA ###");
            }
        }
    }
}

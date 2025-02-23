package com.bibliotecapp.Main;

import com.bibliotecapp.Model.Emprestimo;
import com.bibliotecapp.Services.Biblioteca;
import com.bibliotecapp.Model.Livro;
import com.bibliotecapp.Model.Usuario;
import com.bibliotecapp.Exceptions.LimiteEmprestimosExcedidoException;
import com.bibliotecapp.Exceptions.LivroNaoEncontradoException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            
            System.out.println("\n=== BIBLIOTECAPP ===");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Usuário");
            System.out.println("3. Realizar Empréstimo");
            System.out.println("4. Realizar Devolução");
            System.out.println("5. Consultar Livros Disponíveis");
            System.out.println("6. Consultar Histórico de Empréstimos");
            System.out.println("7. Gerar Relatórios de Uso");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro(biblioteca, scanner);
                    break;
                case 2:
                    cadastrarUsuario(biblioteca, scanner);
                    break;
                case 3:
                    realizarEmprestimo(biblioteca, scanner);
                    break;
                case 4:
                    realizarDevolucao(biblioteca, scanner);
                    break;
                case 5:
                    consultarLivrosDisponiveis(biblioteca);
                    break;
                case 6:
                    consultarHistoricoEmprestimos(biblioteca, scanner);
                    break;
                case 7:
                    gerarRelatorios(biblioteca);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    private static void cadastrarLivro(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== CADASTRAR LIVRO ===");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        System.out.print("Ano de Publicação: ");
        int anoPublicacao = scanner.nextInt();
        scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, anoPublicacao, isbn);
        biblioteca.adicionarLivro(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void cadastrarUsuario(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== CADASTRAR USUÁRIO ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        Usuario usuario = new Usuario(nome, idade);
        biblioteca.adicionarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void realizarEmprestimo(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== REALIZAR EMPRÉSTIMO ===");
        System.out.print("Nome do Usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Título do Livro: ");
        String tituloLivro = scanner.nextLine();

        try {
            Usuario usuario = biblioteca.buscarUsuarioPorNome(nomeUsuario);
            Livro livro = biblioteca.buscarLivroPorTitulo(tituloLivro);
            biblioteca.realizarEmprestimo(usuario, livro);
            System.out.println("Empréstimo realizado com sucesso!");
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (LimiteEmprestimosExcedidoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void realizarDevolucao(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== REALIZAR DEVOLUÇÃO ===");
        System.out.print("Nome do Usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Título do Livro: ");
        String tituloLivro = scanner.nextLine();

        try {
            Usuario usuario = biblioteca.buscarUsuarioPorNome(nomeUsuario);
            Livro livro = biblioteca.buscarLivroPorTitulo(tituloLivro);
            biblioteca.realizarDevolucao(usuario, livro);
            System.out.println("Devolução realizada com sucesso!");
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void consultarLivrosDisponiveis(Biblioteca biblioteca) {
        System.out.println("\n=== LIVROS DISPONÍVEIS ===");
        List<Livro> livrosDisponiveis = biblioteca.consultarLivrosDisponiveis();

        if (livrosDisponiveis.isEmpty()) {
            System.out.println("Nenhum livro disponível no momento.");
        } else {
            for (Livro livro : livrosDisponiveis) {
                System.out.println("Título: " + livro.getTitulo());
                System.out.println("Autor: " + livro.getAutor());
                System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
                System.out.println("ISBN: " + livro.getIsbn());
                System.out.println("-------------------------");
            }
        }
    }

    private static void consultarHistoricoEmprestimos(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== HISTÓRICO DE EMPRÉSTIMOS ===");
        System.out.print("Nome do Usuário: ");
        String nomeUsuario = scanner.nextLine();

        try {
            Usuario usuario = biblioteca.buscarUsuarioPorNome(nomeUsuario);
            List<Emprestimo> historico = biblioteca.consultarHistoricoEmprestimos(usuario);

            if (historico.isEmpty()) {
                System.out.println("Nenhum empréstimo encontrado para o usuário: " + usuario.getNome());
            } else {
                System.out.println("Histórico de empréstimos para " + usuario.getNome() + ":");
                for (Emprestimo emprestimo : historico) {
                    System.out.println("Livro: " + emprestimo.getLivro().getTitulo());
                    System.out.println("Data de Empréstimo: " + emprestimo.getDataEmprestimo());
                    System.out.println("Data de Devolução: " + emprestimo.getDataDevolucao());
                    System.out.println("-------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void gerarRelatorios(Biblioteca biblioteca) {
        System.out.println("\n=== RELATÓRIOS DE USO ===");

        System.out.println("\n--- Livros Mais Emprestados ---");
        Map<Livro, Integer> relatorioLivros = biblioteca.gerarRelatorioLivrosMaisEmprestados();
        if (relatorioLivros.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
        } else {
            for (Map.Entry<Livro, Integer> entry : relatorioLivros.entrySet()) {
                Livro livro = entry.getKey();
                int quantidade = entry.getValue();
                System.out.println("Livro: " + livro.getTitulo() + " | Autor: " + livro.getAutor() + " | Empréstimos: " + quantidade);
            }
        }

        System.out.println("\n--- Usuários com Mais Empréstimos ---");
        Map<Usuario, Integer> relatorioUsuarios = biblioteca.gerarRelatorioUsuariosComMaisEmprestimos();
        if (relatorioUsuarios.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
        } else {
            for (Map.Entry<Usuario, Integer> entry : relatorioUsuarios.entrySet()) {
                Usuario usuario = entry.getKey();
                int quantidade = entry.getValue();
                System.out.println("Usuário: " + usuario.getNome() + " | Empréstimos: " + quantidade);
            }
        }


        System.out.println("\n--- Número Total de Empréstimos ---");
        System.out.println("Total: " + biblioteca.getTotalEmprestimos());
    }

    private static void verificarNotificacoes(Biblioteca biblioteca) {
        System.out.println("\n=== NOTIFICAÇÕES DE ATRASO ===");
        boolean haNotificacoes = false;

        for (Emprestimo emprestimo : biblioteca.getEmprestimos()) {
            biblioteca.notificarAtraso(emprestimo);
            haNotificacoes = true;
        }

        if (!haNotificacoes) {
            System.out.println("Nenhuma notificação de atraso no momento.");
        }
    }
}
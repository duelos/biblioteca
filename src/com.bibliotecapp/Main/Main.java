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
            System.out.println("1. Livros");
            System.out.println("2. Usuários");
            System.out.println("3. Empréstimos");
            System.out.println("4. Relatórios");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuLivros(biblioteca, scanner);
                    break;
                case 2:
                    menuUsuarios(biblioteca, scanner);
                    break;
                case 3:
                    menuEmprestimos(biblioteca, scanner);
                    break;
                case 4:
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


    private static void menuLivros(Biblioteca biblioteca, Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n=== MENU LIVROS ===");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Editar Livro");
            System.out.println("3. Remover Livro");
            System.out.println("4. Consultar Livro");
            System.out.println("5. Exibir livros disponiveis ");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarLivro(biblioteca, scanner);
                    break;
                case 2:
                    editarLivro(biblioteca, scanner);
                    break;
                case 3:
                    removerLivro(biblioteca, scanner);
                    break;
                case 4:
                    consultarLivro(biblioteca, scanner);
                    break;
                case 5:
                    consultarLivrosDisponiveis(biblioteca);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Submenu para Usuários
    private static void menuUsuarios(Biblioteca biblioteca, Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n=== MENU USUÁRIOS ===");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Remover Usuário");
            System.out.println("3. Consultar Usuário");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario(biblioteca, scanner);
                    break;
                case 2:
                    removerUsuario(biblioteca, scanner);
                    break;
                case 3:
                    consultarUsuario(biblioteca, scanner);
                    break;
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    // Submenu para Empréstimos
    private static void menuEmprestimos(Biblioteca biblioteca, Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n=== MENU EMPRÉSTIMOS ===");
            System.out.println("1. Realizar Empréstimo");
            System.out.println("2. Realizar Devolução");
            System.out.println("3. Histórico de Emprestimos");
            System.out.println("4. Exibir Notificações");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    realizarEmprestimo(biblioteca, scanner);
                    break;
                case 2:
                    realizarDevolucao(biblioteca, scanner);
                    break;
                case 3:
                    consultarHistoricoEmprestimos(biblioteca, scanner);
                    break;
                case 4:
                    verificarNotificacoes(biblioteca);
                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
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

    private static void editarLivro(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== EDITAR LIVRO ===");
        System.out.print("Título do Livro a Editar: ");
        String titulo = scanner.nextLine();

        System.out.print("Novo Título: ");
        String novoTitulo = scanner.nextLine();
        System.out.print("Novo Autor: ");
        String novoAutor = scanner.nextLine();
        System.out.print("Novo Ano de Publicação: ");
        int novoAnoPublicacao = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Novo ISBN: ");
        String novoIsbn = scanner.nextLine();

        try {
            biblioteca.editarLivro(titulo, novoTitulo, novoAutor, novoAnoPublicacao, novoIsbn);
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void removerLivro(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== REMOVER LIVRO ===");
        System.out.print("Título do Livro a Remover: ");
        String titulo = scanner.nextLine();

        try {
            biblioteca.removerLivro(titulo);
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void consultarLivro(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== CONSULTAR LIVRO ===");
        System.out.print("Título do Livro: ");
        String titulo = scanner.nextLine();

        try {
            Livro livro = biblioteca.buscarLivroPorTitulo(titulo);
            System.out.println("Detalhes do Livro:");
            System.out.println("Título: " + livro.getTitulo());
            System.out.println("Autor: " + livro.getAutor());
            System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
            System.out.println("ISBN: " + livro.getIsbn());
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void removerUsuario(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== REMOVER USUÁRIO ===");
        System.out.print("Nome do Usuário: ");
        String nomeUsuario = scanner.nextLine();

        try {
            biblioteca.removerUsuario(nomeUsuario);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void consultarUsuario(Biblioteca biblioteca, Scanner scanner) {
        System.out.println("\n=== CONSULTAR USUÁRIO ===");
        System.out.print("Nome do Usuário: ");
        String nomeUsuario = scanner.nextLine();

        try {
            Usuario usuario = biblioteca.consultarUsuario(nomeUsuario);
            System.out.println("Detalhes do Usuário:");
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Idade: " + usuario.getIdade());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
package com.bibliotecapp.Services;

import com.bibliotecapp.Model.Emprestimo;
import com.bibliotecapp.Model.Livro;
import com.bibliotecapp.Model.Usuario;
import com.bibliotecapp.Exceptions.LivroNaoEncontradoException;
import com.bibliotecapp.Exceptions.LimiteEmprestimosExcedidoException;
import com.bibliotecapp.Interfaces.Notificavel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Biblioteca implements Notificavel {
    private List<Livro> acervo;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        acervo.add(livro);
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Livro buscarLivroPorTitulo(String titulo) throws LivroNaoEncontradoException {
        for (Livro livro : acervo) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        throw new LivroNaoEncontradoException("Livro não encontrado: " + titulo);
    }

    public void realizarEmprestimo(Usuario usuario, Livro livro) throws LimiteEmprestimosExcedidoException, LivroNaoEncontradoException {
        if (usuario.getLivrosEmprestados().size() >= 3) {
            throw new LimiteEmprestimosExcedidoException("Limite de empréstimos excedido para o usuário: " + usuario.getNome());
        }

        if (!acervo.contains(livro)) {
            throw new LivroNaoEncontradoException("Livro não encontrado: " + livro.getTitulo());
        }


        usuario.emprestarLivro(livro);
        Emprestimo emprestimo = new Emprestimo(usuario, livro, LocalDate.now());
        emprestimos.add(emprestimo);
    }

    public void realizarDevolucao(Usuario usuario, Livro livro) {
        usuario.devolverLivro(livro);
        emprestimos.removeIf(e -> e.getLivro().equals(livro) && e.getUsuario().equals(usuario));
    }

    @Override
    public void notificarAtraso(Emprestimo emprestimo) {
        if (LocalDate.now().isAfter(emprestimo.getDataDevolucao())) {
            System.out.println("Notificação de atraso para o usuário: " + emprestimo.getUsuario().getNome());
        }
    }

    public List<Livro> consultarLivrosDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : acervo) {
            // Verifica se o livro não está emprestado
            boolean emprestado = false;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getLivro().equals(livro)) {
                    emprestado = true;
                    break;
                }
            }
            if (!emprestado) {
                livrosDisponiveis.add(livro);
            }
        }
        return livrosDisponiveis;
    }

    public Usuario buscarUsuarioPorNome(String nome) throws Exception {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        throw new Exception("Usuário não encontrado: " + nome);
    }

    public List<Emprestimo> consultarHistoricoEmprestimos(Usuario usuario) {
        List<Emprestimo> historico = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().equals(usuario)) {
                historico.add(emprestimo);
            }
        }
        return historico;
    }


        public Map<Livro, Integer> gerarRelatorioLivrosMaisEmprestados() {
            Map<Livro, Integer> relatorio = new HashMap<>();

            for (Emprestimo emprestimo : emprestimos) {
                Livro livro = emprestimo.getLivro();
                relatorio.put(livro, relatorio.getOrDefault(livro, 0) + 1);
            }

            return relatorio;
        }

        public Map<Usuario, Integer> gerarRelatorioUsuariosComMaisEmprestimos() {
            Map<Usuario, Integer> relatorio = new HashMap<>();

            for (Emprestimo emprestimo : emprestimos) {
                Usuario usuario = emprestimo.getUsuario();
                relatorio.put(usuario, relatorio.getOrDefault(usuario, 0) + 1);
            }

            return relatorio;
        }

        public int getTotalEmprestimos() {
            return emprestimos.size();

        }

        public List<Emprestimo> getEmprestimos() {
        return emprestimos;
        }
    }


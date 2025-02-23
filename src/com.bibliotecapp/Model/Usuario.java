package com.bibliotecapp.Model;

import com.bibliotecapp.Exceptions.LimiteEmprestimosExcedidoException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private int idade;
    private List<Livro> livrosEmprestados;

    public Usuario(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.livrosEmprestados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void emprestarLivro(Livro livro) throws LimiteEmprestimosExcedidoException {
        if (livrosEmprestados.size() >= 3) {
            throw new LimiteEmprestimosExcedidoException("Limite de empréstimos excedido para o usuário: " + nome);
        }
        livrosEmprestados.add(livro);
    }

    public void devolverLivro(Livro livro) {
        livrosEmprestados.remove(livro);
    }
}
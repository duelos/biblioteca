package com.bibliotecapp.Model;

public class Livro extends ItemBiblioteca {
    private String isbn;

    public Livro(String titulo, String autor, int anoPublicacao, String isbn) {
        super(titulo, autor, anoPublicacao);
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Setters para os atributos herdados de ItemBiblioteca
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    @Override
    public String getTipo() {
        return "Livro";
    }
}
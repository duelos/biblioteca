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

    @Override
    public String getTipo() {
        return "Livro";
    }
}

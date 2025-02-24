package com.bibliotecapp.Model;

public abstract class ItemBiblioteca {
    protected String titulo;
    protected String autor;
    protected int anoPublicacao;

    public ItemBiblioteca(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public abstract String getTipo();
}
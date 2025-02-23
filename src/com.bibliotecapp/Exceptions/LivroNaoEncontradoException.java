package com.bibliotecapp.Exceptions;

public class LivroNaoEncontradoException extends Exception {
    public LivroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}

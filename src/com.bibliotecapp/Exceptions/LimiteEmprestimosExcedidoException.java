package com.bibliotecapp.Exceptions;

public class LimiteEmprestimosExcedidoException extends Exception {
    public LimiteEmprestimosExcedidoException(String mensagem) {
        super(mensagem);
    }
}
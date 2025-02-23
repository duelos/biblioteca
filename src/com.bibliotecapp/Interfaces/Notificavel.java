package com.bibliotecapp.Interfaces;

import com.bibliotecapp.Model.Emprestimo;

public interface Notificavel {
    void notificarAtraso(Emprestimo emprestimo);
}
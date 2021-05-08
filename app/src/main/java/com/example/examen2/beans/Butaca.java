package com.example.examen2.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Butaca implements Serializable {

    private int id_butaca;
    private int fila;
    private int columna;
    private ArrayList<Entrada> entrada;

    public int getId_butaca() {
        return id_butaca;
    }

    public void setId_butaca(int id_butaca) {
        this.id_butaca = id_butaca;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public ArrayList<Entrada> getEntrada() {
        return entrada;
    }

    public void setEntrada(ArrayList<Entrada> entrada) {
        this.entrada = entrada;
    }
}

package com.example.examen2.beans;

import java.util.ArrayList;

public class Compra {

    private int id_compra;
    private String fecha_compra;
    private ArrayList<Entrada> entrada;

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public ArrayList<Entrada> getEntrada() {
        return entrada;
    }

    public void setEntrada(ArrayList<Entrada> entrada) {
        this.entrada = entrada;
    }
}

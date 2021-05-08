package com.example.examen2.beans;

public class Tarifa {

    private int id_tarifa;
    private String nombre_tarifa;
    private float importe;
    private Entrada entrada;

    public int getId_tarifa() {
        return id_tarifa;
    }

    public void setId_tarifa(int id_tarifa) {
        this.id_tarifa = id_tarifa;
    }

    public String getNombre_tarifa() {
        return nombre_tarifa;
    }

    public void setNombre_tarifa(String nombre_tarifa) {
        this.nombre_tarifa = nombre_tarifa;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }
}

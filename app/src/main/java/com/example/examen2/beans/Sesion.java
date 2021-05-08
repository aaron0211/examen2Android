package com.example.examen2.beans;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Sesion implements Serializable {

    private int id_sesion;
    private String hora;
    private String fecha;
    private ArrayList<Entrada> entrada;
    private Sala sala;
    private Pelicula pelicula;

    public int getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Entrada> getEntrada() {
        return entrada;
    }

    public void setEntrada(ArrayList<Entrada> entrada) {
        this.entrada = entrada;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }
}

package com.example.examen2.beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Sala implements Serializable {

    private int id_sala;
    private String planta;
    private Sesion sesion;
    private ArrayList<Butaca> butaca;
    private Cine cine;

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getPlanta() {
        return planta;
    }

    public void setPlanta(String planta) {
        this.planta = planta;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public ArrayList<Butaca> getButaca() {
        return butaca;
    }

    public void setButaca(ArrayList<Butaca> butaca) {
        this.butaca = butaca;
    }

    public Cine getCine() {
        return cine;
    }

    public void setCine(Cine cine) {
        this.cine = cine;
    }
}

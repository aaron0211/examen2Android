package com.example.examen2.beans;

import java.util.ArrayList;

public class Usuario {

    private int id_usuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private String fecha_registro;
    private ArrayList<Compra> compra;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(String fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public ArrayList<Compra> getCompra() {
        return compra;
    }

    public void setCompra(ArrayList<Compra> compra) {
        this.compra = compra;
    }
}

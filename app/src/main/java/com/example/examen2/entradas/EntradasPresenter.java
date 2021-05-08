package com.example.examen2.entradas;

import com.example.examen2.beans.Entrada;

import java.util.ArrayList;

public class EntradasPresenter implements EntradasContract.Presenter{

    private EntradasContract.View view;
    private EntradasModel entradasModel;

    public EntradasPresenter(EntradasContract.View view){
        this.view = view;
        this.entradasModel = new EntradasModel();
    }

    @Override
    public void getEntradas(int id_sesion) {
        entradasModel.getEntradasWS(new EntradasContract.Model.OnEntradasListener() {
            @Override
            public void resolve(ArrayList<Entrada> entradas) {
                view.success(entradas);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        },id_sesion);
    }
}

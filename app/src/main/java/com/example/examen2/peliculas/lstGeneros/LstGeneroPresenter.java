package com.example.examen2.peliculas.lstGeneros;

import com.example.examen2.beans.Genero;

import java.util.ArrayList;

public class LstGeneroPresenter implements LstGeneroContract.Presenter{

    private LstGeneroContract.View view;
    private LstGeneroModel lstGeneroModel;

    public LstGeneroPresenter(LstGeneroContract.View view){
        this.view = view;
        this.lstGeneroModel = new LstGeneroModel();
    }


    @Override
    public void getGeneros() {
        lstGeneroModel.getGenerosWS(new LstGeneroContract.Model.OnLstGenerosListener() {
            @Override
            public void resolve(ArrayList<Genero> generos) {
                view.success(generos);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        });
    }
}

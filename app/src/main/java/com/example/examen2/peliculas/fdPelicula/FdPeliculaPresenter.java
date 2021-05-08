package com.example.examen2.peliculas.fdPelicula;

import com.example.examen2.beans.Sesion;

import java.util.ArrayList;

public class FdPeliculaPresenter implements FdPeliculaContract.Presenter{

    private FdPeliculaModel fdPeliculaModel;
    private FdPeliculaContract.View view;

    public FdPeliculaPresenter(FdPeliculaContract.View view){
        this.view = view;
        this.fdPeliculaModel = new FdPeliculaModel();
    }

    @Override
    public void getCines(int id_pelicula) {
        fdPeliculaModel.getCinesWS(new FdPeliculaContract.Model.OnLstCinesListener() {
            @Override
            public void resolve(ArrayList<Sesion> sesions) {
                view.success(sesions);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        }, id_pelicula);
    }
}

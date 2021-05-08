package com.example.examen2.peliculas.lstPeliculasTOP;

import com.example.examen2.beans.Pelicula;

import java.util.ArrayList;

public class LstPeliculasTopPresenter implements LstPeliculasTopContract.Presenter{

    private LstPeliculasTopContract.View view;
    private LstPeliculasTopModel lstPeliculasTopModel;

    public LstPeliculasTopPresenter(LstPeliculasTopContract.View view){
        this.view = view;
        this.lstPeliculasTopModel = new LstPeliculasTopModel();
    }

    @Override
    public void getPeliculasTop() {
        lstPeliculasTopModel.getPeliculasTopWS(new LstPeliculasTopContract.Model.OnLstPeliculasTopListener() {
            @Override
            public void resolve(ArrayList<Pelicula> peliculas) {
                view.success(peliculas);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        });
    }
}

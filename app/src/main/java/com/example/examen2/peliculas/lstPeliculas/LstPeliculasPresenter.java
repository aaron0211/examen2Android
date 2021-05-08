package com.example.examen2.peliculas.lstPeliculas;

import com.example.examen2.beans.Pelicula;

import java.util.ArrayList;

public class LstPeliculasPresenter implements LstPeliculasContract.Presenter{

    private LstPeliculasModel lstPeliculasModel;
    private LstPeliculasContract.View view;

    public LstPeliculasPresenter(LstPeliculasContract.View view){
        this.view = view;
        this.lstPeliculasModel = new LstPeliculasModel();
    }

    @Override
    public void getPeliculas() {
        lstPeliculasModel.getPeliculasWS(new LstPeliculasContract.Model.OnLstPeliculasListener() {
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

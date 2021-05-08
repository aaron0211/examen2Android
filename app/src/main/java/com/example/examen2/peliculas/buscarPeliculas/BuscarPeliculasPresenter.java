package com.example.examen2.peliculas.buscarPeliculas;

import com.example.examen2.beans.Pelicula;

import java.util.ArrayList;

public class BuscarPeliculasPresenter implements BuscarPeliculasContract.Presenter{

    private BuscarPeliculasContract.View view;
    private BuscarPeliculasModel buscarPeliculasModel;

    public BuscarPeliculasPresenter(BuscarPeliculasContract.View view){
        this.view = view;
        this.buscarPeliculasModel = new BuscarPeliculasModel();
    }

    @Override
    public void getPeliculasGenero(String genero) {
        buscarPeliculasModel.getPeliculasGenero(new BuscarPeliculasContract.Model.OnBuscarPeliculas() {
            @Override
            public void resolve(ArrayList<Pelicula> peliculas) {
                view.successBuscar(peliculas);
            }

            @Override
            public void reject(String error) {
                view.errorBuscar(error);
            }
        },genero);
    }

    @Override
    public void getPeliculasSinopsis(String sinopsis) {
        buscarPeliculasModel.getPeliculasSinopsis(new BuscarPeliculasContract.Model.OnBuscarPeliculas() {
            @Override
            public void resolve(ArrayList<Pelicula> peliculas) {
                view.successBuscar(peliculas);
            }

            @Override
            public void reject(String error) {
                view.errorBuscar(error);
            }
        },sinopsis);
    }
}

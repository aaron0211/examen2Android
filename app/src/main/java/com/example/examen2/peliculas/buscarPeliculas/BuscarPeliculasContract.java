package com.example.examen2.peliculas.buscarPeliculas;

import com.example.examen2.beans.Pelicula;

import java.util.ArrayList;

public interface BuscarPeliculasContract {
    interface View{
        void successBuscar(ArrayList<Pelicula> peliculas);
        void errorBuscar(String error);
    }

    interface Presenter{
        void getPeliculasGenero(String genero);
        void getPeliculasSinopsis(String sinopsis);
    }

    interface Model{
        void getPeliculasGenero(OnBuscarPeliculas onBuscarPeliculas,String genero);
        void getPeliculasSinopsis(OnBuscarPeliculas onBuscarPeliculas,String sinopsis);

        interface OnBuscarPeliculas{
            void resolve(ArrayList<Pelicula> peliculas);
            void reject(String error);
        }
    }
}

package com.example.examen2.peliculas.lstPeliculasTOP;

import com.example.examen2.beans.Pelicula;
import com.example.examen2.peliculas.lstPeliculas.LstPeliculasContract;

import java.util.ArrayList;

public interface LstPeliculasTopContract {
    interface View{
        void success(ArrayList<Pelicula> peliculas);
        void error(String message);
    }

    interface Presenter{
        void getPeliculasTop();
    }

    interface Model{
        void getPeliculasTopWS(OnLstPeliculasTopListener onLstPeliculasTopListener);

        interface OnLstPeliculasTopListener{
            void resolve(ArrayList<Pelicula> peliculas);
            void reject(String error);
        }
    }
}

package com.example.examen2.peliculas.fdPelicula;

import com.example.examen2.beans.Sesion;

import java.util.ArrayList;

public interface FdPeliculaContract {
    interface View{
        void success(ArrayList<Sesion> sesions);
        void error(String message);
    }

    interface Presenter{
        void getCines(int id_pelicula);
    }

    interface Model{
        void getCinesWS(OnLstCinesListener onLstCinesListener, int id_pelicula);

        interface OnLstCinesListener{
            void resolve(ArrayList<Sesion> sesions);
            void reject(String error);
        }
    }
}

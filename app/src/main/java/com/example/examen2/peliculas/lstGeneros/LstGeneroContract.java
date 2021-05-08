package com.example.examen2.peliculas.lstGeneros;

import com.example.examen2.beans.Genero;

import java.util.ArrayList;

public interface LstGeneroContract {
    interface View{
        void success(ArrayList<Genero> generos);
        void error(String message);
    }

    interface Presenter{
        void getGeneros();
    }

    interface Model{
        void getGenerosWS(OnLstGenerosListener onLstGenerosListener);

        interface OnLstGenerosListener{
            void resolve(ArrayList<Genero> generos);
            void reject(String error);
        }
    }
}

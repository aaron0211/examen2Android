package com.example.examen2.entradas;

import com.example.examen2.beans.Entrada;

import java.util.ArrayList;

public interface EntradasContract {
    interface View{
        void success(ArrayList<Entrada> entradas);
        void error(String error);
    }

    interface Presenter{
        void getEntradas(int id_sesion);
    }

    interface Model{
        void getEntradasWS(OnEntradasListener onEntradasListener,int id_sesion);

        interface OnEntradasListener{
            void resolve(ArrayList<Entrada> entradas);
            void reject(String error);
        }
    }
}

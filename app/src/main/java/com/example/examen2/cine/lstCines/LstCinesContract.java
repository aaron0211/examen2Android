package com.example.examen2.cine.lstCines;

import com.example.examen2.beans.Cine;

import java.util.ArrayList;

public interface LstCinesContract {
    interface View{
        void success(ArrayList<Cine> cines);
        void error(String message);
    }

    interface Presenter{
        void getCines();
    }

    interface Model{
        void getCinesWS(OnLstCinesListener onLstCinesListener);

        interface OnLstCinesListener{
            void resolve(ArrayList<Cine> cines);
            void reject(String error);
        }
    }
}

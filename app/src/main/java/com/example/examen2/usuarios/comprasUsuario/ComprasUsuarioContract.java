package com.example.examen2.usuarios.comprasUsuario;

import com.example.examen2.beans.Compra;

import java.util.ArrayList;

public interface ComprasUsuarioContract {
    interface View{
        void success(ArrayList<Compra> compras);
        void error(String message);
    }

    interface Presenter{
        void getCompras(int id_usuario);
    }

    interface Model{
        void getCompraUsuarioWS(OnComprasUsuarioListener onComprasUsuarioListener, int id_usuario);

        interface OnComprasUsuarioListener{
            void resolve(ArrayList<Compra> compras);
            void reject(String error);
        }
    }
}

package com.example.examen2.usuarios.loginUsuario;

import com.example.examen2.beans.Usuario;

public interface LoginUsuarioContract {
    interface View{
        void success(Usuario usuario);
        void error(String message);
    }

    interface Presenter{
        void getUsuarioLogin(String email,String password);
    }

    interface Model{
        void getUsuarioLoginWS(OnLoginUsuarioListener onLoginUsuarioListener,String email, String password);

        interface OnLoginUsuarioListener{
            void resolve(Usuario usuario);
            void reject(String error);
        }
    }
}

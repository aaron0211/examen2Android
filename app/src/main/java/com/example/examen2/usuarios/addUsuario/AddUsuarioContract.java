package com.example.examen2.usuarios.addUsuario;

import com.example.examen2.beans.Usuario;

public interface AddUsuarioContract {
    interface View{
        void success(String success);
        void error(String error);
    }

    interface Presenter{
        void postUser(Usuario usuario);
    }

    interface Model{
        void postUsuarioWS(OnPostUsuarioListener onPostUsuarioListener, Usuario usuario);

        interface OnPostUsuarioListener{
            void resolve(String success);
            void reject(String error);
        }
    }
}

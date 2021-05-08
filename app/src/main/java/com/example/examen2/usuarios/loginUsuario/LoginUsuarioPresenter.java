package com.example.examen2.usuarios.loginUsuario;

import com.example.examen2.beans.Usuario;

public class LoginUsuarioPresenter implements LoginUsuarioContract.Presenter{

    private LoginUsuarioModel loginUsuarioModel;
    private LoginUsuarioContract.View view;

    public LoginUsuarioPresenter(LoginUsuarioContract.View view){
        this.view = view;
        this.loginUsuarioModel = new LoginUsuarioModel();
    }


    @Override
    public void getUsuarioLogin(String email, String password) {
        loginUsuarioModel.getUsuarioLoginWS(new LoginUsuarioContract.Model.OnLoginUsuarioListener() {
            @Override
            public void resolve(Usuario usuario) {
                view.success(usuario);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        }, email, password);
    }
}

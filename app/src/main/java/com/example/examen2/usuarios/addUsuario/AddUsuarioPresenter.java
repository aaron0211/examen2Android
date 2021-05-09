package com.example.examen2.usuarios.addUsuario;

import com.example.examen2.beans.Usuario;

public class AddUsuarioPresenter implements AddUsuarioContract.Presenter{

    private AddUsuarioContract.View view;
    private AddUsuarioModel addUsuarioModel;

    public AddUsuarioPresenter(AddUsuarioContract.View view){
        this.view = view;
        this.addUsuarioModel = new AddUsuarioModel();
    }

    @Override
    public void postUser(Usuario usuario) {
        addUsuarioModel.postUsuarioWS(new AddUsuarioContract.Model.OnPostUsuarioListener() {
            @Override
            public void resolve(String success) {
                view.success(success);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        }, usuario);
    }
}

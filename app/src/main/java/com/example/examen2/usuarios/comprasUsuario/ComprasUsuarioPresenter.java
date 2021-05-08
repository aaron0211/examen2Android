package com.example.examen2.usuarios.comprasUsuario;

import com.example.examen2.beans.Compra;

import java.util.ArrayList;

public class ComprasUsuarioPresenter implements ComprasUsuarioContract.Presenter{

    private ComprasUsuarioModel comprasUsuarioModel;
    private ComprasUsuarioContract.View view;

    public ComprasUsuarioPresenter(ComprasUsuarioContract.View view){
        this.view = view;
        this.comprasUsuarioModel = new ComprasUsuarioModel();
    }

    @Override
    public void getCompras(int id_usuario) {
        comprasUsuarioModel.getCompraUsuarioWS(new ComprasUsuarioContract.Model.OnComprasUsuarioListener() {
            @Override
            public void resolve(ArrayList<Compra> compras) {
                view.success(compras);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        }, id_usuario);
    }
}

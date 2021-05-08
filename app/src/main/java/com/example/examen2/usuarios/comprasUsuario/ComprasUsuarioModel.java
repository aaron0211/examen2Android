package com.example.examen2.usuarios.comprasUsuario;

import com.example.examen2.beans.Compra;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComprasUsuarioModel implements ComprasUsuarioContract.Model, Callback<ArrayList<Compra>> {

    OnComprasUsuarioListener onComprasUsuarioListener;

    @Override
    public void getCompraUsuarioWS(OnComprasUsuarioListener onComprasUsuarioListener, int id_usuario) {
        this.onComprasUsuarioListener = onComprasUsuarioListener;

        Call<ArrayList<Compra>> call = ApiAdapter.getApiService().getCompraUsuario(id_usuario);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Compra>> call, Response<ArrayList<Compra>> response) {
        if (response.isSuccessful()){
            onComprasUsuarioListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Compra>> call, Throwable t) {
        onComprasUsuarioListener.reject(t.getMessage());
    }
}

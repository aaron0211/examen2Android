package com.example.examen2.entradas;

import com.example.examen2.beans.Entrada;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EntradasModel implements EntradasContract.Model, Callback<ArrayList<Entrada>> {

    OnEntradasListener onEntradasListener;

    @Override
    public void getEntradasWS(OnEntradasListener onEntradasListener, int id_sesion) {
        this.onEntradasListener = onEntradasListener;

        Call<ArrayList<Entrada>> listCall = ApiAdapter.getApiService().getEntradasSesion(id_sesion);
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Entrada>> call, Response<ArrayList<Entrada>> response) {
        if (response.isSuccessful()){
            onEntradasListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Entrada>> call, Throwable t) {
        onEntradasListener.reject(t.getMessage());
    }
}

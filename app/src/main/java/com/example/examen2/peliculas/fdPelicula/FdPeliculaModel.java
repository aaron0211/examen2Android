package com.example.examen2.peliculas.fdPelicula;

import com.example.examen2.beans.Cine;
import com.example.examen2.beans.Sesion;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FdPeliculaModel implements FdPeliculaContract.Model, Callback<ArrayList<Sesion>> {

    OnLstCinesListener onLstCinesListener;

    @Override
    public void getCinesWS(OnLstCinesListener onLstCinesListener, int id_pelicula) {
        this.onLstCinesListener = onLstCinesListener;

        Call<ArrayList<Sesion>> call = ApiAdapter.getApiService().getSesionByPelicula(id_pelicula);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Sesion>> call, Response<ArrayList<Sesion>> response) {
        if (response.isSuccessful()){
            onLstCinesListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Sesion>> call, Throwable t) {
        onLstCinesListener.reject(t.getMessage());
    }
}

package com.example.examen2.peliculas.lstGeneros;

import com.example.examen2.beans.Genero;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstGeneroModel implements LstGeneroContract.Model, Callback<ArrayList<Genero>> {

    OnLstGenerosListener onLstGenerosListener;

    @Override
    public void getGenerosWS(OnLstGenerosListener onLstGenerosListener) {
        this.onLstGenerosListener = onLstGenerosListener;

        Call<ArrayList<Genero>> listCall = ApiAdapter.getApiService().getGeneros();
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Genero>> call, Response<ArrayList<Genero>> response) {
        if (response.isSuccessful()){
            onLstGenerosListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Genero>> call, Throwable t) {
        onLstGenerosListener.reject(t.getMessage());
    }
}

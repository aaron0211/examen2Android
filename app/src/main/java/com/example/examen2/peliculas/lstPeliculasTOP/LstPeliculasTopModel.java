package com.example.examen2.peliculas.lstPeliculasTOP;

import com.example.examen2.beans.Pelicula;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstPeliculasTopModel implements LstPeliculasTopContract.Model, Callback<ArrayList<Pelicula>> {

    OnLstPeliculasTopListener onLstPeliculasTopListener;

    @Override
    public void getPeliculasTopWS(OnLstPeliculasTopListener onLstPeliculasTopListener) {
        this.onLstPeliculasTopListener = onLstPeliculasTopListener;

        Call<ArrayList<Pelicula>> listCall = ApiAdapter.getApiService().getTop10();
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Pelicula>> call, Response<ArrayList<Pelicula>> response) {
        if (response.isSuccessful()){
            onLstPeliculasTopListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Pelicula>> call, Throwable t) {
        onLstPeliculasTopListener.reject(t.getMessage());
    }
}

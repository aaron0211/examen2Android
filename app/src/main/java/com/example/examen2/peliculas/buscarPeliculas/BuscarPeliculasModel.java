package com.example.examen2.peliculas.buscarPeliculas;

import com.example.examen2.beans.Pelicula;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarPeliculasModel implements BuscarPeliculasContract.Model, Callback<ArrayList<Pelicula>> {

    OnBuscarPeliculas onBuscarPeliculas;

    @Override
    public void getPeliculasGenero(OnBuscarPeliculas onBuscarPeliculas, String genero) {
        this.onBuscarPeliculas = onBuscarPeliculas;

        Call<ArrayList<Pelicula>> listCall = ApiAdapter.getApiService().getPeliculasGenero(genero);
        listCall.enqueue(this);
    }

    @Override
    public void getPeliculasSinopsis(OnBuscarPeliculas onBuscarPeliculas, String sinopsis) {
        this.onBuscarPeliculas = onBuscarPeliculas;

        Call<ArrayList<Pelicula>> listCall = ApiAdapter.getApiService().getPeliculasSinopsis(sinopsis);
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Pelicula>> call, Response<ArrayList<Pelicula>> response) {
        if (response.isSuccessful()){
            onBuscarPeliculas.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Pelicula>> call, Throwable t) {
        onBuscarPeliculas.reject(t.getMessage());
    }
}

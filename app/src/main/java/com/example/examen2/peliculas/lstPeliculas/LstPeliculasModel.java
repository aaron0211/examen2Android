package com.example.examen2.peliculas.lstPeliculas;

import com.example.examen2.beans.Pelicula;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstPeliculasModel implements LstPeliculasContract.Model, Callback<ArrayList<Pelicula>> {

    OnLstPeliculasListener onLstPeliculasListener;

    @Override
    public void getPeliculasWS(OnLstPeliculasListener onLstPeliculasListener) {
        this.onLstPeliculasListener = onLstPeliculasListener;

        Call<ArrayList<Pelicula>> peliculaCall = ApiAdapter.getApiService().getPeliculas();
        peliculaCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Pelicula>> call, Response<ArrayList<Pelicula>> response) {
        if (response.isSuccessful()){
            onLstPeliculasListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Pelicula>> call, Throwable t) {
        onLstPeliculasListener.reject(t.getMessage());
    }
}

package com.example.examen2.service;

import com.example.examen2.beans.Cine;
import com.example.examen2.beans.Compra;
import com.example.examen2.beans.DTO.EntradaDTO;
import com.example.examen2.beans.Entrada;
import com.example.examen2.beans.Genero;
import com.example.examen2.beans.Pelicula;
import com.example.examen2.beans.Sesion;
import com.example.examen2.beans.Tarifa;
import com.example.examen2.beans.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("peliculas")
    Call<ArrayList<Pelicula>> getPeliculas();

    @GET("generos")
    Call<ArrayList<Genero>> getGeneros();

    @GET("peliculas/top10")
    Call<ArrayList<Pelicula>> getTop10();

    @GET("peliculas/genero")
    Call<ArrayList<Pelicula>> getPeliculasGenero(@Query("genero")String genero);

    @GET("entradas/sesion")
    Call<ArrayList<Entrada>> getEntradasSesion(@Query("sesion")int id_sesion);

    @GET("peliculas/sinopsis")
    Call<ArrayList<Pelicula>> getPeliculasSinopsis(@Query("sinopsis")String sinopsis);

    @GET("usuario")
    Call<Usuario> getUsuarioLogin(@Query("email")String email, @Query("password")String password);

    @GET("compras")
    Call<ArrayList<Compra>> getCompraUsuario(@Query("id")int id_usuario);

    @GET("sesiones/pelicula")
    Call<ArrayList<Sesion>> getSesionByPelicula(@Query("id")int id_pelicula);

    @POST("compra")
    Call<Compra> comprarEntradas(@Body ArrayList<EntradaDTO> json);
}

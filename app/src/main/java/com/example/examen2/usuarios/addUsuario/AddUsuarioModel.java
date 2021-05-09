package com.example.examen2.usuarios.addUsuario;

import com.example.examen2.beans.Usuario;
import com.example.examen2.service.ApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddUsuarioModel implements AddUsuarioContract.Model, Callback<Usuario> {

    OnPostUsuarioListener onPostUsuarioListener;

    @Override
    public void postUsuarioWS(OnPostUsuarioListener onPostUsuarioListener, Usuario usuario) {
        this.onPostUsuarioListener = onPostUsuarioListener;

        Call<Usuario> usuarioCall = ApiAdapter.getApiService().postUsuario(usuario);
        usuarioCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        if (response.isSuccessful()){
            onPostUsuarioListener.resolve("Usuario " + response.body().getNombre() + " añadido");
        }
    }

    @Override
    public void onFailure(Call<Usuario> call, Throwable t) {
        onPostUsuarioListener.reject(t.getMessage());
    }
}

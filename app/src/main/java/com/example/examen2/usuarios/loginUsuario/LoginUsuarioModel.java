package com.example.examen2.usuarios.loginUsuario;

import com.example.examen2.beans.Usuario;
import com.example.examen2.service.ApiAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUsuarioModel implements LoginUsuarioContract.Model, Callback<Usuario> {

    OnLoginUsuarioListener onLoginUsuarioListener;

    @Override
    public void getUsuarioLoginWS(OnLoginUsuarioListener onLoginUsuarioListener, String email, String password) {
        this.onLoginUsuarioListener = onLoginUsuarioListener;
        Call<Usuario> usuarioCall = ApiAdapter.getApiService().getUsuarioLogin(email,password);
        usuarioCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
        if (response.isSuccessful()){
            onLoginUsuarioListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<Usuario> call, Throwable t) {
        onLoginUsuarioListener.reject(t.getMessage());
    }
}

package com.example.examen2.cine.lstCines;

import com.example.examen2.beans.Cine;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LstCinesModel implements LstCinesContract.Model, Callback<ArrayList<Cine>> {

    OnLstCinesListener onLstCinesListener;

    @Override
    public void getCinesWS(OnLstCinesListener onLstCinesListener) {
        this.onLstCinesListener = onLstCinesListener;

        Call<ArrayList<Cine>> listCall = ApiAdapter.getApiService().getCines();
        listCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<Cine>> call, Response<ArrayList<Cine>> response) {
        if (response.isSuccessful()){
            onLstCinesListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<ArrayList<Cine>> call, Throwable t) {
        onLstCinesListener.reject(t.getMessage());
    }
}

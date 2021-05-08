package com.example.examen2.entradas.compraEntradas;

import com.example.examen2.beans.Compra;
import com.example.examen2.beans.DTO.EntradaDTO;
import com.example.examen2.beans.Entrada;
import com.example.examen2.beans.Tarifa;
import com.example.examen2.service.ApiAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompraEntradasModel implements CompraEntradasContract.Model, Callback<Compra> {

    OnCompraListener onCompraListener;

    @Override
    public void postCompraWS(OnCompraListener onCompraListener, ArrayList<EntradaDTO> json) {
        this.onCompraListener = onCompraListener;

        Call<Compra> compraCall = ApiAdapter.getApiService().comprarEntradas(json);
        compraCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<Compra> call, Response<Compra> response) {
        if (response.isSuccessful()){
            onCompraListener.resolve(response.body());
        }
    }

    @Override
    public void onFailure(Call<Compra> call, Throwable t) {
        onCompraListener.reject(t.getMessage());
    }
}

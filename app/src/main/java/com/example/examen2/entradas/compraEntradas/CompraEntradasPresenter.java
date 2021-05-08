package com.example.examen2.entradas.compraEntradas;

import com.example.examen2.beans.Compra;
import com.example.examen2.beans.DTO.EntradaDTO;

import java.util.ArrayList;

public class CompraEntradasPresenter implements CompraEntradasContract.Presenter{

    private CompraEntradasModel compraEntradasModel;
    private CompraEntradasContract.View view;

    public CompraEntradasPresenter(CompraEntradasContract.View view){
        this.view = view;
        compraEntradasModel = new CompraEntradasModel();
    }


    @Override
    public void postCompra(ArrayList<EntradaDTO> json) {
        compraEntradasModel.postCompraWS(new CompraEntradasContract.Model.OnCompraListener() {
            @Override
            public void resolve(Compra compra) {
                view.success(compra);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        },json);
    }
}

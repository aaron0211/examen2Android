package com.example.examen2.entradas.compraEntradas;

import com.example.examen2.beans.Compra;
import com.example.examen2.beans.DTO.EntradaDTO;
import com.example.examen2.beans.Entrada;
import com.example.examen2.beans.Tarifa;

import java.util.ArrayList;

public interface CompraEntradasContract {
    interface View{
        void success(Compra compra);
        void error(String message);
    }

    interface Presenter{
        void postCompra(ArrayList<EntradaDTO> json);
    }

    interface Model{
        void postCompraWS(OnCompraListener onCompraListener,ArrayList<EntradaDTO> json);

        interface OnCompraListener{
            void resolve(Compra compra);
            void reject(String error);
        }
    }
}

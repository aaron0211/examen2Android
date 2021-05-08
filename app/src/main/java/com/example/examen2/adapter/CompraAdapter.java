package com.example.examen2.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen2.R;
import com.example.examen2.beans.Compra;
import com.example.examen2.usuarios.comprasUsuario.DetalleCompraActivity;

import java.util.ArrayList;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.CompraViewHolder> {

    private ArrayList<Compra> lstCompra;

    public CompraAdapter(ArrayList<Compra> lstCompra) {
        this.lstCompra = lstCompra;
    }

    public static class CompraViewHolder extends RecyclerView.ViewHolder{
        private TextView compra;

        public CompraViewHolder(@NonNull View itemView) {
            super(itemView);
            compra = itemView.findViewById(R.id.row_compras_textView);
        }
    }

    @NonNull
    @Override
    public CompraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_compras,parent,false);
        return new CompraViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CompraViewHolder holder, int position) {
        Compra compra = lstCompra.get(position);

        float precio = 0;

        for (int i=0;i<compra.getEntrada().size();i++){
            precio = precio + compra.getEntrada().get(i).getImporte();
        }

        holder.compra.setText(compra.getFecha_compra() + " " + precio + "€");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetalleCompraActivity.class);
                intent.putExtra("idCompra",compra.getId_compra());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstCompra.size();
    }
}

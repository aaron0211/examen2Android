package com.example.examen2.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen2.entradas.EntradasActivity;
import com.example.examen2.R;
import com.example.examen2.beans.Sesion;


import java.util.ArrayList;

public class CineSesionAdapter extends RecyclerView.Adapter<CineSesionAdapter.CineSesionViewHolder>{

    private ArrayList<Sesion> lstSesions;

    public CineSesionAdapter(ArrayList<Sesion> lstSesions){
        this.lstSesions = lstSesions;
    }

    public static class CineSesionViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombreCine,tvSesion;

        public CineSesionViewHolder(View view){
            super(view);
            tvNombreCine = view.findViewById(R.id.cine_sesion_row_textView_Cine);
            tvSesion = view.findViewById(R.id.cine_sesion_row_textView_Sesion);
        }
    }

    @NonNull
    @Override
    public CineSesionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cine_sesion_row,parent,false);
        return new CineSesionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CineSesionViewHolder holder, int position) {
        Sesion sesion = lstSesions.get(position);

        holder.tvNombreCine.setText(sesion.getSala().getCine().getNombre());
        holder.tvSesion.setText(sesion.getFecha() + " " + sesion.getHora());

        holder.tvSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EntradasActivity.class);
                intent.putExtra("sesion",sesion);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstSesions.size();
    }
}
package com.example.examen2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen2.R;
import com.example.examen2.beans.Cine;

import java.util.ArrayList;

public class CineAdapter extends RecyclerView.Adapter<CineAdapter.CineViewHolder> {

    private ArrayList<Cine> lstCines;

    public static class CineViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNombre;

        public CineViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.row_cine_name);
        }
    }

    public CineAdapter (ArrayList<Cine> lstCines){
        this.lstCines = lstCines;
    }

    @NonNull
    @Override
    public CineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_cine,parent,false);
        return new CineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CineViewHolder holder, int position) {
        Cine cine = lstCines.get(position);

        holder.tvNombre.setText(cine.getNombre());
    }

    @Override
    public int getItemCount() {
        return lstCines.size();
    }
}

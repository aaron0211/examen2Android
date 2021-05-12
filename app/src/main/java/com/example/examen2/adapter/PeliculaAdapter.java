package com.example.examen2.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen2.R;
import com.example.examen2.beans.Pelicula;
import com.example.examen2.peliculas.fdPelicula.FdPeliculaActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder>{

    private ArrayList<Pelicula> lstPeliculas;

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder{
        public ImageView img;
        public RatingBar ratingBar;

        public PeliculaViewHolder(View v){
            super(v);
            img = v.findViewById(R.id.row_pelicula_ivImg);
            ratingBar = v.findViewById(R.id.row_pelicula_rtRating);
        }
    }

    public PeliculaAdapter(ArrayList<Pelicula> lstPeliculas) {
        this.lstPeliculas = lstPeliculas;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_pelicula,parent,false);
        return new PeliculaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula pelicula = lstPeliculas.get(position);
        float estrellas = (pelicula.getPuntuacion()/(float)pelicula.getVotos());

        Picasso.get().load(pelicula.getUrl()).into(holder.img);
        holder.ratingBar.setRating(estrellas);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), FdPeliculaActivity.class);
                intent.putExtra("pelicula",pelicula);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstPeliculas.size();
    }
}

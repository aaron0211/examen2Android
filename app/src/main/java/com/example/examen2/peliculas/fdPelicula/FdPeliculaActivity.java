package com.example.examen2.peliculas.fdPelicula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.examen2.R;
import com.example.examen2.beans.Pelicula;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class FdPeliculaActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private Pelicula pelicula;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fd_pelicula);
        fragmentManager = getSupportFragmentManager();

        pelicula = (Pelicula) getIntent().getSerializableExtra("pelicula");

        youTubePlayerView = findViewById(R.id.activity_fd_pelicula_youtube);
        bottomNavigationView = findViewById(R.id.activity_fd_pelicula_bottomNavigationView);
        titulo = findViewById(R.id.activity_fd_pelicula_textView_titulo);
        titulo.setText(pelicula.getTitulo());
        getLifecycle().addObserver(youTubePlayerView);

        initBottom();

        showFragment();

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                String videoId = pelicula.getUrl();
                youTubePlayer.loadVideo(videoId,0);
            }
        });
    }

    private void initBottom(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_nav_1:
                        showFragment();
                        return true;
                    case R.id.menu_nav_2:
                        showFragmentSinopsis();
                        return true;
                }
                return false;
            }
        });
    }

    private void showFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Cine_Sesion_Fragment fragment = Cine_Sesion_Fragment.newInstance(pelicula.getId_pelicula());
        transaction.replace(R.id.activity_fd_pelicula_layout,fragment).commit();
    }

    private void showFragmentSinopsis(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        SinopsisFragment fragment = SinopsisFragment.newInstance(pelicula.getSinopsis(),pelicula.getFecha_estreno());
        transaction.replace(R.id.activity_fd_pelicula_layout,fragment).commit();
    }
}
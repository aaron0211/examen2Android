package com.example.examen2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.example.examen2.cine.lstCines.LstCinesFragment;
import com.example.examen2.peliculas.buscarPeliculas.BuscarPeliculasFragment;
import com.example.examen2.peliculas.lstPeliculas.LstPeliculasFragment;
import com.example.examen2.peliculas.lstPeliculasTOP.LstPeliculasTopFragment;
import com.example.examen2.usuarios.logedUsuario.LogedUsuarioFragment;
import com.example.examen2.usuarios.loginUsuario.LoginUsuarioFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();

        bottomNavigationView = findViewById(R.id.activity_home_BottomNavigation);

        if (getIntent().getStringExtra("Login") == null){
            showFragmentHome();
        }else {
            showFragmentUser();
        }

        initBottomNavigation();
    }

    private void initBottomNavigation(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_nav_1:
                        showFragmentHome();
                        return true;
                    case R.id.menu_nav_2:
                        showTop10();
                        return true;
                    case R.id.menu_nav_3:
                        showFragmentBuscar();
                        return true;
                    case R.id.menu_nav_4:
                        showFragmentCine();
                        return true;
                    case R.id.menu_nav_5:
                        showFragmentUser();
                        return true;
                }
                return false;
            }
        });
    }

    private void showFragmentHome(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        LstPeliculasFragment lstPeliculasFragment = LstPeliculasFragment.newInstance();
        transaction.replace(R.id.activity_home_layout,lstPeliculasFragment);
        transaction.commit();
    }

    private void showTop10(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        LstPeliculasTopFragment fragment = LstPeliculasTopFragment.newInstance();
        transaction.replace(R.id.activity_home_layout,fragment).addToBackStack(null).commit();
    }

    private void showFragmentBuscar(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        BuscarPeliculasFragment fragment = BuscarPeliculasFragment.newInstance();
        transaction.replace(R.id.activity_home_layout,fragment).addToBackStack(null).commit();
    }

    private void showFragmentCine(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
        LstCinesFragment fragment = LstCinesFragment.newInstance();
        transaction.replace(R.id.activity_home_layout,fragment).addToBackStack(null).commit();
    }

    private void showFragmentUser(){
        Boolean registrado;
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        registrado = sharedPreferences.getBoolean("Registrado",false);

        if (!registrado) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
            LoginUsuarioFragment fragment = LoginUsuarioFragment.newInstance();
            transaction.replace(R.id.activity_home_layout, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }else {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit);
            LogedUsuarioFragment fragment = LogedUsuarioFragment.newInstance(sharedPreferences.getString("Nombre","aaron"),sharedPreferences.getString("Apellidos","retave"),
                                            sharedPreferences.getString("Email","aaron@aaron.com"),sharedPreferences.getString("Password","1234"),sharedPreferences.getInt("Id",1));
            transaction.replace(R.id.activity_home_layout,fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
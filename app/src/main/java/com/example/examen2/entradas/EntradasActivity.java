package com.example.examen2.entradas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.examen2.R;
import com.example.examen2.beans.Entrada;
import com.example.examen2.beans.Sesion;

import java.util.ArrayList;

public class EntradasActivity extends AppCompatActivity implements EntradasContract.View {

    private Sesion sesion;
    private EntradasPresenter entradasPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entradas);

        entradasPresenter = new EntradasPresenter(this);

        sesion = (Sesion) getIntent().getSerializableExtra("sesion");
        getIntent().putExtra("sesion",sesion);

        entradasPresenter.getEntradas(sesion.getId_sesion());

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        EntradasFragment fragment = EntradasFragment.newInstance();
        transaction.replace(R.id.activity_entradas_layout,fragment).commit();

    }

    @Override
    public void success(ArrayList<Entrada> entradas) {
        getIntent().putExtra("entradas",entradas);
    }

    @Override
    public void error(String error) {

    }
}
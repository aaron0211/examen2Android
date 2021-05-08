package com.example.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean registrado;
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        registrado = sharedPreferences.getBoolean("Registrado",false);
        if (!registrado) {
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
                this.finish();
            }, 2500);
        }else {
            startActivity(new Intent(this,HomeActivity.class));
        }
    }
}
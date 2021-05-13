package com.example.examen2.usuarios.comprasUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen2.R;
import com.example.examen2.roomDB.ComprasRoom;
import com.example.examen2.roomDB.RoomDB;

import java.util.ArrayList;
import java.util.List;

public class DetalleCompraActivity extends AppCompatActivity {

    private ImageView img;
    private TextView titulo,cine,fecha;

    private RoomDB database;
    private ComprasRoom data;

    private int mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_compra);

        initComponents();

        mId = getIntent().getIntExtra("idCompra",0);

        database = RoomDB.getInstance(getApplicationContext());

        if(database.comprasDAO().getByIdCompra(mId) ==  null){
            Toast.makeText(getApplicationContext(), R.string.fail_detail,Toast.LENGTH_LONG).show();
            finish();
        }else {
            data = database.comprasDAO().getByIdCompra(mId);

            titulo.setText(data.getPelicula());
            cine.setText(data.getCine());
            fecha.setText(data.getFecha());

            Bitmap bmp = BitmapFactory.decodeByteArray(data.getImagen(),0,data.getImagen().length);
            img.setImageBitmap(bmp);

            AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
            animation.setDuration(1000);
            animation.setStartOffset(2500);
            animation.setFillAfter(true);
            img.startAnimation(animation);

        }
    }

    private void initComponents(){
        img = findViewById(R.id.activity_detalle_compra_img);
        titulo = findViewById(R.id.activity_detalle_compra_textView_titulo);
        cine = findViewById(R.id.activity_detalle_compra_textView_cine);
        fecha = findViewById(R.id.activity_detalle_compra_textView_fecha);
    }
}
package com.example.examen2.entradas.compraEntradas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen2.HomeActivity;
import com.example.examen2.R;
import com.example.examen2.beans.Compra;
import com.example.examen2.beans.DTO.EntradaDTO;
import com.example.examen2.beans.Entrada;
import com.example.examen2.beans.Tarifa;
import com.example.examen2.roomDB.ComprasRoom;
import com.example.examen2.roomDB.RoomDB;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CompraEntradasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CompraEntradasFragment extends Fragment implements CompraEntradasContract.View {

    private TextView tvPaypal, tvTarjeta, tvBizum, tvReserva;

    private ArrayList<Tarifa> lstTarifa = new ArrayList<>();
    private ArrayList<Entrada> lstEntradas = new ArrayList<>();
    private int idUsuario;

    RoomDB database;
    ComprasRoom data;


    private CompraEntradasPresenter presenter;

    public CompraEntradasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CompraEntradasFragment.
     */
    public static CompraEntradasFragment newInstance() {
        CompraEntradasFragment fragment = new CompraEntradasFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        lstEntradas = (ArrayList<Entrada>) intent.getSerializableExtra("lstEntradas");
        lstTarifa = (ArrayList<Tarifa>) intent.getSerializableExtra("lstTarifas");
        idUsuario = (int) intent.getSerializableExtra("id_usuario");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_compra_entradas, container, false);

        initComponents(v);

        presenter = new CompraEntradasPresenter(this);

        ArrayList<EntradaDTO> lstDTO = new ArrayList<>();

        for (int i=0;i<lstEntradas.size();i++){
            EntradaDTO entradaDTO = new EntradaDTO();
            entradaDTO.setId(idUsuario);
            entradaDTO.setButaca(lstEntradas.get(i).getButaca().getId_butaca());
            entradaDTO.setSesion(lstEntradas.get(i).getSesion().getId_sesion());
            entradaDTO.setTarifa(lstTarifa.get(i).getNombre_tarifa());

            lstDTO.add(entradaDTO);
        }

        database = RoomDB.getInstance(getContext());

        tvPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.postCompra(lstDTO);
            }
        });

        tvTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.postCompra(lstDTO);
            }
        });

        tvBizum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.postCompra(lstDTO);
            }
        });

        tvReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.postCompra(lstDTO);
            }
        });

        return v;
    }

    private void initComponents(View view){
        tvPaypal = view.findViewById(R.id.fragment_compra_entradas_textView_paypal);
        tvTarjeta = view.findViewById(R.id.fragment_compra_entradas_textView_tarjeta);
        tvBizum = view.findViewById(R.id.fragment_compra_entradas_textView_bizum);
        tvReserva = view.findViewById(R.id.fragment_compra_entradas_textView_reserva);
    }

    @Override
    public void success(Compra compra) {
        QRCodeWriter writer = new QRCodeWriter();
        Bitmap bmp = null;
        try {
            BitMatrix bitMatrix = writer.encode(String.valueOf(getActivity().getContentScene()), BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }

        ByteArrayOutputStream baos = null;

        baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG,100,baos);

        data = new ComprasRoom();
        data.setId_compra(compra.getId_compra());
        data.setId_usuario(idUsuario);
        data.setPelicula(lstEntradas.get(0).getSesion().getPelicula().getTitulo());
        data.setCine(lstEntradas.get(0).getSesion().getSala().getCine().getNombre());
        data.setFecha(lstEntradas.get(0).getSesion().getFecha() + " " +lstEntradas.get(0).getSesion().getHora());
        data.setImagen(baos.toByteArray());
        try {
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        database = RoomDB.getInstance(getContext());

        database.comprasDAO().insert(data);

        Toast.makeText(getContext(),"Compra realizada con éxito",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getContext(), HomeActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void error(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }
}
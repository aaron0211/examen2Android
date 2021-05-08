package com.example.examen2.entradas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.examen2.R;
import com.example.examen2.beans.Entrada;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EntradasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EntradasFragment extends Fragment {

    private int normal,menos13,joven,mas65, max, total, disponibles;
    private Button mas_normal, menos_normal, mas_13, menos_13, mas_joven, menos_joven, mas_65, menos_65, pagar;
    private TextView tvNormal, tv13, tvJoven, tv65, tvTotal;

    public EntradasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param id_sala Parameter 1.
//     * @param id_sesion Parameter 2.
     * @return A new instance of fragment EntradasFragment.
     */
    public static EntradasFragment newInstance() {
        EntradasFragment fragment = new EntradasFragment();
        Bundle args = new Bundle();
//        args.putInt(ARG_EXTRA_IDSALA, id_sala);
//        args.putInt(ARG_EXTRA_IDSESION, id_sesion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mIdSala = getArguments().getInt(ARG_EXTRA_IDSALA);
//            mIdSesion = getArguments().getInt(ARG_EXTRA_IDSESION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_entradas, container, false);

        initComponents(v);

        initButtons();
        normal = 0;
        menos13 = 0;
        joven = 0;
        mas65 = 0;
        max = 0;
        total = 0;

        return v;
    }


    private void initComponents(View v){
        mas_normal = v.findViewById(R.id.fragment_entradas_button_mas_normal);
        menos_normal = v.findViewById(R.id.fragment_entradas_button_menos_normal);
        mas_13 = v.findViewById(R.id.fragment_entradas_button_mas_13);
        menos_13 = v.findViewById(R.id.fragment_entradas_button_menos_13);
        mas_joven = v.findViewById(R.id.fragment_entradas_button_mas_joven);
        menos_joven = v.findViewById(R.id.fragment_entradas_button_menos_joven);
        mas_65 = v.findViewById(R.id.fragment_entradas_button_mas_65);
        menos_65 = v.findViewById(R.id.fragment_entradas_button_menos_65);
        tvNormal = v.findViewById(R.id.fragment_entradas_tvNormal_cantidad);
        tv13 = v.findViewById(R.id.fragment_entradas_tv_13_cantidad);
        tvJoven = v.findViewById(R.id.fragment_entradas_tv_joven_cantidad);
        tv65 = v.findViewById(R.id.fragment_entradas_tv_65_cantidad);
        pagar = v.findViewById(R.id.fragment_entradas_button_pagar);
        tvTotal = v.findViewById(R.id.fragment_entradas_tv_total_pagar);
    }

    private void initButtons(){
        activarButtons();
        mas_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal = normal+1;
                tvNormal.setText(String.valueOf(normal));
                actualizarTotal();
                activarButtons();
            }
        });
        menos_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normal = normal-1;
                tvNormal.setText(String.valueOf(normal));
                actualizarTotal();
                activarButtons();
            }
        });

        mas_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menos13 = menos13+1;
                tv13.setText(String.valueOf(menos13));
                actualizarTotal();
                activarButtons();
            }
        });
        menos_13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menos13 = menos13-1;
                tv13.setText(String.valueOf(menos13));
                actualizarTotal();
                activarButtons();
            }
        });

        mas_joven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joven = joven+1;
                tvJoven.setText(String.valueOf(joven));
                actualizarTotal();
                activarButtons();
            }
        });
        menos_joven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joven = joven-1;
                tvJoven.setText(String.valueOf(joven));
                actualizarTotal();
                activarButtons();
            }
        });

        mas_65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mas65 = mas65+1;
                tv65.setText(String.valueOf(mas65));
                actualizarTotal();
                activarButtons();
            }
        });
        menos_65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mas65 = mas65-1;
                tv65.setText(String.valueOf(mas65));
                actualizarTotal();
                activarButtons();
            }
        });

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ButacasFragment fragment = ButacasFragment.newInstance(total,Integer.valueOf(tvNormal.getText().toString()),
                                            Integer.valueOf(tv13.getText().toString()),Integer.valueOf(tvJoven.getText().toString()),
                                            Integer.valueOf(tv65.getText().toString()));
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_entradas_layout,fragment).addToBackStack(null).commit();
            }
        });
    }

    private void actualizarTotal(){
        float pagar = (float) ((7.7 * Integer.valueOf(String.valueOf(tvNormal.getText()))) +
                (5 * Integer.valueOf(String.valueOf(tv13.getText()))) +
                (5.9 * Integer.valueOf(String.valueOf(tvJoven.getText()))) +
                (3.5 * Integer.valueOf(String.valueOf(tv65.getText()))));

        tvTotal.setText(pagar + " €");
    }

    private void activarButtons(){
        total = Integer.valueOf(tvNormal.getText().toString())+
                Integer.valueOf(tv13.getText().toString())+Integer.valueOf(tvJoven.getText().toString())+
                Integer.valueOf(tv65.getText().toString());

        max = Integer.valueOf(String.valueOf(tvNormal.getText()))+Integer.valueOf(String.valueOf(tv13.getText()))+Integer.valueOf(String.valueOf(tvJoven.getText()))+
                Integer.valueOf(String.valueOf(tv65.getText()));
        if (max == 7){
            mas_normal.setEnabled(false);
            mas_13.setEnabled(false);
            mas_joven.setEnabled(false);
            mas_65.setEnabled(false);
        }else{
            mas_normal.setEnabled(true);
            mas_13.setEnabled(true);
            mas_joven.setEnabled(true);
            mas_65.setEnabled(true);
        }
        if (normal == 0){
            menos_normal.setEnabled(false);
        }else {
            menos_normal.setEnabled(true);
        }
        if (menos13 == 0){
            menos_13.setEnabled(false);
        }else {
            menos_13.setEnabled(true);
        }
        if (joven == 0){
            menos_joven.setEnabled(false);
        }else {
            menos_joven.setEnabled(true);
        }
        if (mas65 == 0){
            menos_65.setEnabled(false);
        }else {
            menos_65.setEnabled(true);
        }
        if (total == 0){
            pagar.setEnabled(false);
        }else {
            pagar.setEnabled(true);
        }
    }
}
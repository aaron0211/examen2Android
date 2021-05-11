package com.example.examen2.entradas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examen2.HomeActivity;
import com.example.examen2.R;
import com.example.examen2.beans.Butaca;
import com.example.examen2.beans.Entrada;
import com.example.examen2.beans.Sesion;
import com.example.examen2.beans.Tarifa;
import com.example.examen2.entradas.compraEntradas.CompraEntradasFragment;
import com.example.examen2.usuarios.loginUsuario.LoginUsuarioFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Observable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ButacasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButacasFragment extends Fragment {

    private static final String ARG_EXTRA_TOTAL = "ARG_EXTRA_TOTAL";
    private static final String ARG_EXTRA_NORMAL = "ARG_EXTRA_NORMAL";
    private static final String ARG_EXTRA_MENOS13 = "ARG_EXTRA_MENOS13";
    private static final String ARG_EXTRA_JOVEN = "ARG_EXTRA_JOVEN";
    private static final String ARG_EXTRA_MAS65 = "ARG_EXTRA_MAS65";

    private int mTotal;
    private int mNormal;
    private int mMenos13;
    private int mJoven;
    private int mMas65;

    private Sesion sesion;
    private ArrayList<Entrada> entradas = new ArrayList<>();
    private Butaca butaca1, butaca2, butaca3, butaca4, butaca5, butaca6, butaca7;
    private ImageView ivImg;
    private TextView tvTitulo, tvCine, tvFecha, tvHora;
    private CheckBox cb1, cb2, cb3, cb4, cb5, cb6, cb7;
    private Button btContinuar;
    private int contador;

    private ObservableList<Butaca> butacas = new ObservableArrayList<>();
    private ArrayList<Tarifa> lstTarifa = new ArrayList<>();
    private ArrayList<Entrada> lstEntradas = new ArrayList<>();

    public ButacasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param total Parameter 1.
     * @param normal Parameter 2.
     * @param menos13 Parameter 3.
     * @param joven Parameter 4.
     * @param mas65 Parameter 5.
     * @return A new instance of fragment ButacasFragment.
     */
    public static ButacasFragment newInstance(int total, int normal, int menos13, int joven, int mas65) {
        ButacasFragment fragment = new ButacasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EXTRA_TOTAL, total);
        args.putInt(ARG_EXTRA_NORMAL, normal);
        args.putInt(ARG_EXTRA_MENOS13, menos13);
        args.putInt(ARG_EXTRA_JOVEN, joven);
        args.putInt(ARG_EXTRA_MAS65, mas65);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTotal = getArguments().getInt(ARG_EXTRA_TOTAL);
            mNormal = getArguments().getInt(ARG_EXTRA_NORMAL);
            mMenos13 = getArguments().getInt(ARG_EXTRA_MENOS13);
            mJoven = getArguments().getInt(ARG_EXTRA_JOVEN);
            mMas65 = getArguments().getInt(ARG_EXTRA_MAS65);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = getActivity().getIntent();
        sesion = (Sesion) intent.getSerializableExtra("sesion");
        entradas = (ArrayList<Entrada>) intent.getSerializableExtra("entradas");

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_butacas, container, false);

        contador = 0;

        initComponents(v);
        initListeners();

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean registrado;
                final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                registrado = sharedPreferences.getBoolean("Registrado",false);

                if (!registrado) {
                    Intent intent1 = new Intent(getContext(), HomeActivity.class);
                    intent1.putExtra("Login","Ir a login");
                    getActivity().startActivity(intent1);
                }else {

                    for (int i=0;i<mTotal;i++){
                        Entrada entrada = new Entrada();
                        Butaca butaca = butacas.get(i);
                        entrada.setButaca(butaca);
                        entrada.setSesion(sesion);

                        lstEntradas.add(entrada);
                    }

                    for (int i=0;i<mNormal;i++){
                        Tarifa tarifa = new Tarifa();
                        tarifa.setNombre_tarifa("Normal");

                        lstTarifa.add(tarifa);
                    }

                    for (int i=0;i<mMenos13;i++){
                        Tarifa tarifa = new Tarifa();
                        tarifa.setNombre_tarifa("-13 años");

                        lstTarifa.add(tarifa);
                    }

                    for (int i=0;i<mJoven;i++){
                        Tarifa tarifa = new Tarifa();
                        tarifa.setNombre_tarifa("C.joven/uni");

                        lstTarifa.add(tarifa);
                    }

                    for (int i=0;i<mMas65;i++){
                        Tarifa tarifa = new Tarifa();
                        tarifa.setNombre_tarifa("65 años");

                        lstTarifa.add(tarifa);
                    }

                    getActivity().getIntent().putExtra("lstEntradas", lstEntradas);
                    getActivity().getIntent().putExtra("lstTarifas", lstTarifa);
                    getActivity().getIntent().putExtra("id_usuario", sharedPreferences.getInt("Id",12));

                    CompraEntradasFragment fragment = CompraEntradasFragment.newInstance();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.enter,R.anim.exit,R.anim.pop_enter,R.anim.pop_exit)
                            .replace(R.id.activity_entradas_layout,fragment).addToBackStack(null).commit();
                }
            }
        });

        return v;
    }

    private void initComponents(View v){
        ivImg = v.findViewById(R.id.fragment_butacas_im);
        tvTitulo = v.findViewById(R.id.fragment_butacas_tv_titulo);
        tvCine = v.findViewById(R.id.fragment_butacas_tv_cine);
        tvFecha = v.findViewById(R.id.fragment_butacas_tv_fecha);
        tvHora = v.findViewById(R.id.fragment_butacas_tv_hora);
        btContinuar = v.findViewById(R.id.fragment_butacas_button_continue);
        cb1 = v.findViewById(R.id.fragment_butacas_chekbox_1);
        cb2 = v.findViewById(R.id.fragment_butacas_chekbox_2);
        cb3 = v.findViewById(R.id.fragment_butacas_chekbox_3);
        cb4 = v.findViewById(R.id.fragment_butacas_chekbox_4);
        cb5 = v.findViewById(R.id.fragment_butacas_chekbox_5);
        cb6 = v.findViewById(R.id.fragment_butacas_chekbox_6);
        cb7 = v.findViewById(R.id.fragment_butacas_chekbox_7);

        Picasso.get().load(sesion.getPelicula().getUrl()).into(ivImg);
        tvTitulo.setText(tvTitulo.getText()+" "+sesion.getPelicula().getTitulo());
        tvCine.setText(tvCine.getText()+" "+sesion.getSala().getCine().getNombre());
        tvFecha.setText(tvFecha.getText()+" "+sesion.getFecha());
        tvHora.setText(tvHora.getText()+" "+sesion.getHora());

        butaca1 = new Butaca();
        butaca2 = new Butaca();
        butaca3 = new Butaca();
        butaca4 = new Butaca();
        butaca5 = new Butaca();
        butaca6 = new Butaca();
        butaca7 = new Butaca();

        butaca1.setId_butaca(sesion.getSala().getButaca().get(0).getId_butaca());
        butaca2.setId_butaca(sesion.getSala().getButaca().get(1).getId_butaca());
        butaca3.setId_butaca(sesion.getSala().getButaca().get(2).getId_butaca());
        butaca4.setId_butaca(sesion.getSala().getButaca().get(3).getId_butaca());
        butaca5.setId_butaca(sesion.getSala().getButaca().get(4).getId_butaca());
        butaca6.setId_butaca(sesion.getSala().getButaca().get(5).getId_butaca());
        butaca7.setId_butaca(sesion.getSala().getButaca().get(6).getId_butaca());

        for (int i=0;i<entradas.size();i++){
            if (butaca1.getId_butaca() == entradas.get(i).getButaca().getId_butaca()){
                cb1.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cb1.setButtonTintList(getContext().getColorStateList(R.color.grey));
                }
                cb1.setClickable(false);
            }
            if (butaca2.getId_butaca() == entradas.get(i).getButaca().getId_butaca()){
                cb2.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cb2.setButtonTintList(getContext().getColorStateList(R.color.grey));
                }
                cb2.setClickable(false);
            }
            if (butaca3.getId_butaca() == entradas.get(i).getButaca().getId_butaca()){
                cb3.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cb3.setButtonTintList(getContext().getColorStateList(R.color.grey));
                }
                cb3.setClickable(false);
            }
            if (butaca4.getId_butaca() == entradas.get(i).getButaca().getId_butaca()){
                cb4.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cb4.setButtonTintList(getContext().getColorStateList(R.color.grey));
                }
                cb4.setClickable(false);
            }
            if (butaca5.getId_butaca() == entradas.get(i).getButaca().getId_butaca()){
                cb5.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cb5.setButtonTintList(getContext().getColorStateList(R.color.grey));
                }
                cb5.setClickable(false);
            }
            if (butaca6.getId_butaca() == entradas.get(i).getButaca().getId_butaca()){
                cb6.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cb6.setButtonTintList(getContext().getColorStateList(R.color.grey));
                }
                cb6.setClickable(false);
            }
            if (butaca7.getId_butaca() == entradas.get(i).getButaca().getId_butaca()){
                cb7.setChecked(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cb7.setButtonTintList(getContext().getColorStateList(R.color.grey));
                }
                cb7.setClickable(false);
            }
        }

    }

    private void initListeners(){
        comprobarTotal();
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb1.isChecked()){
                    contador = contador+1;
                    butacas.add(butaca1);
                }else {
                    contador = contador-1;
                    butacas.remove(butaca1);
                }
                comprobarTotal();
            }
        });

        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb2.isChecked()){
                    contador = contador+1;
                    butacas.add(butaca2);
                }else {
                    contador = contador-1;
                    butacas.remove(butaca2);
                }
                comprobarTotal();
            }
        });

        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb3.isChecked()){
                    contador = contador+1;
                    butacas.add(butaca3);
                }else {
                    contador = contador-1;
                    butacas.remove(butaca3);
                }
                comprobarTotal();
            }
        });

        cb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb4.isChecked()){
                    contador = contador+1;
                    butacas.add(butaca4);
                }else {
                    contador = contador-1;
                    butacas.remove(butaca4);
                }
                comprobarTotal();
            }
        });

        cb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb5.isChecked()){
                    contador = contador+1;
                    butacas.add(butaca5);
                }else {
                    contador = contador-1;
                    butacas.remove(butaca5);
                }
                comprobarTotal();
            }
        });

        cb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb6.isChecked()){
                    contador = contador+1;
                    butacas.add(butaca6);
                }else {
                    contador = contador-1;
                    butacas.remove(butaca6);
                }
                comprobarTotal();
            }
        });

        cb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cb7.isChecked()){
                    contador = contador+1;
                    butacas.add(butaca7);
                }else {
                    contador = contador-1;
                    butacas.remove(butaca7);
                }
                comprobarTotal();
            }
        });
    }

    private void comprobarTotal(){
        if (mTotal == contador){
            if (!cb1.isChecked()) desactivarCheckBox(cb1);
            if (!cb2.isChecked()) desactivarCheckBox(cb2);
            if (!cb3.isChecked()) desactivarCheckBox(cb3);
            if (!cb4.isChecked()) desactivarCheckBox(cb4);
            if (!cb5.isChecked()) desactivarCheckBox(cb5);
            if (!cb6.isChecked()) desactivarCheckBox(cb6);
            if (!cb7.isChecked()) desactivarCheckBox(cb7);
            btContinuar.setEnabled(true);
        }else {
            if (!cb1.isEnabled()) activarCheckbox(cb1);
            if (!cb2.isEnabled()) activarCheckbox(cb2);
            if (!cb3.isEnabled()) activarCheckbox(cb3);
            if (!cb4.isEnabled()) activarCheckbox(cb4);
            if (!cb5.isEnabled()) activarCheckbox(cb5);
            if (!cb6.isEnabled()) activarCheckbox(cb6);
            if (!cb7.isEnabled()) activarCheckbox(cb7);
            btContinuar.setEnabled(false);
        }
    }

    private void activarCheckbox(CheckBox checkBox){
        checkBox.setEnabled(true);
    }

    private void desactivarCheckBox(CheckBox checkBox){
        checkBox.setEnabled(false);
    }
}
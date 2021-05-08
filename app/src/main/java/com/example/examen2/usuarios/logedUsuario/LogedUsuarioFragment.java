package com.example.examen2.usuarios.logedUsuario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.examen2.HomeActivity;
import com.example.examen2.R;
import com.example.examen2.usuarios.comprasUsuario.ComprasFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogedUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogedUsuarioFragment extends Fragment {

    private TextView tvNombre, tvApellidos, tvCompras;
    private Button btLogout;

    private static final String ARG_EXTRA_NOMBRE = "ARG_EXTRA_NOMBRE";
    private static final String ARG_EXTRA_APELLIDOS = "ARG_EXTRA_APELLIDOS";
    private static final String ARG_EXTRA_EMAIL = "ARG_EXTRA_EMAIL";
    private static final String ARG_EXTRA_PASSWORD = "ARG_EXTRA_PASSWORD";
    private static final String ARG_EXTRA_ID = "ARG_EXTRA_ID";

    private String mNombre;
    private String mApellidos;
    private String mEmail;
    private String mPassword;
    private int mId;

    public LogedUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param nombre Parameter 1.
     * @param apellidos Parameter 2.
     * @param email Parameter 3.
     * @param password Parameter 4.
     * @param id Parameter 5.
     * @return A new instance of fragment LogedUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogedUsuarioFragment newInstance(String nombre, String apellidos, String email, String password, int id) {
        LogedUsuarioFragment fragment = new LogedUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EXTRA_NOMBRE, nombre);
        args.putString(ARG_EXTRA_APELLIDOS, apellidos);
        args.putString(ARG_EXTRA_EMAIL, email);
        args.putString(ARG_EXTRA_PASSWORD, password);
        args.putInt(ARG_EXTRA_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNombre = getArguments().getString(ARG_EXTRA_NOMBRE);
            mApellidos = getArguments().getString(ARG_EXTRA_APELLIDOS);
            mEmail = getArguments().getString(ARG_EXTRA_EMAIL);
            mPassword = getArguments().getString(ARG_EXTRA_PASSWORD);
            mId = getArguments().getInt(ARG_EXTRA_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_loged_usuario, container, false);

        initComponents(v);

        tvNombre.setText(mNombre);
        tvApellidos.setText(mApellidos);

        tvCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComprasFragment comprasFragment = ComprasFragment.newInstance(mId);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.activity_home_layout,comprasFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        btLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                Intent i = new Intent(getContext(),HomeActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    private void initComponents(View v){
        tvNombre = v.findViewById(R.id.fragment_loged_usuario_textView_nombre);
        tvApellidos = v.findViewById(R.id.fragment_loged_usuario_textView_apellidos);
        tvCompras = v.findViewById(R.id.fragment_loged_usuario_textView_compras);
        btLogout = v.findViewById(R.id.fragment_loged_usuario_button_logout);
    }
}
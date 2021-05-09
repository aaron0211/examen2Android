package com.example.examen2.usuarios.addUsuario;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.examen2.R;
import com.example.examen2.beans.Usuario;
import com.example.examen2.usuarios.loginUsuario.LoginUsuarioFragment;

import java.time.LocalDate;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddUsuarioFragment extends Fragment implements AddUsuarioContract.View {

    private EditText etNombre,etApellidos,etEmail,etPass,etPass2;
    private Button btnAdd;
    private AddUsuarioPresenter addUsuarioPresenter;

    public AddUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddUsuarioFragment newInstance() {
        AddUsuarioFragment fragment = new AddUsuarioFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_usuario, container, false);

        addUsuarioPresenter = new AddUsuarioPresenter(this);

        initComponents(v);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= etNombre.getText().toString();
                String apellidos = etApellidos.getText().toString();
                String email = etEmail.getText().toString();
                String pass1 = etPass.getText().toString();
                String pass2 = etPass2.getText().toString();
                if (nombre.isEmpty()){
                    etNombre.setError("Campo obligatorio");
                    return;
                }
                if (apellidos.isEmpty()){
                    etApellidos.setError("Campo obligatorio");
                    return;
                }
                if (email.isEmpty()){
                    etEmail.setError("Campo obligatorio");
                    return;
                }
                if (pass1.isEmpty()){
                    etPass.setError("Campo obligatorio");
                    return;
                }
                if (pass1.equals(pass2)){
                    Usuario usuario = new Usuario();
                    usuario.setNombre(String.valueOf(etNombre.getText()));
                    usuario.setApellidos(String.valueOf(etApellidos.getText()));
                    usuario.setEmail(String.valueOf(etEmail.getText()));
                    usuario.setPassword(String.valueOf(etPass.getText()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        usuario.setFecha_registro(String.valueOf(LocalDate.now()));
                    }

                    addUsuarioPresenter.postUser(usuario);
                }else {
                    etPass.setError("No son iguales");
                    etPass2.setError("No son iguales");
                }
            }
        });

        return v;
    }

    private void initComponents(View v){
        etNombre = v.findViewById(R.id.fragment_add_usuario_textinput_name);
        etApellidos = v.findViewById(R.id.fragment_add_usuario_textinput_apellidos);
        etEmail = v.findViewById(R.id.fragment_add_usuario_textinput_email);
        etPass = v.findViewById(R.id.fragment_add_usuario_textinput_pass);
        etPass2 = v.findViewById(R.id.fragment_add_usuario_textinput_pass2);
        btnAdd = v.findViewById(R.id.fragment_add_riders_button_add);
    }

    @Override
    public void success(String success) {
        Toast.makeText(getContext(),success,Toast.LENGTH_LONG).show();
        LoginUsuarioFragment loginUsuarioFragment = LoginUsuarioFragment.newInstance();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_home_layout,loginUsuarioFragment).addToBackStack(null).commit();
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }
}
package com.example.examen2.usuarios.loginUsuario;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.examen2.R;
import com.example.examen2.beans.Usuario;
import com.example.examen2.usuarios.logedUsuario.LogedUsuarioFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginUsuarioFragment extends Fragment implements LoginUsuarioContract.View {

    private LoginUsuarioPresenter loginUsuarioPresenter;
    private ProgressBar progressBar;
    private EditText etEmail, etPassword;
    private Button btLogin, btAdd;

    public LoginUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginUsuarioFragment newInstance() {
        LoginUsuarioFragment fragment = new LoginUsuarioFragment();
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
        View v = inflater.inflate(R.layout.fragment_login_usuario, container, false);

        initComponents(v);
        progressBar.setVisibility(View.GONE);
        loginUsuarioPresenter = new LoginUsuarioPresenter(this);
        initButtons();

        return v;
    }

    private void initComponents(View v){
        etEmail = v.findViewById(R.id.fragment_login_usuario_textinput_email);
        etPassword = v.findViewById(R.id.fragment_login_usuario_textinput_password);
        btLogin = v.findViewById(R.id.fragment_login_usuario_button_login);
        btAdd = v.findViewById(R.id.fragment_login_usuario_button_add);
        progressBar = v.findViewById(R.id.fragment_login_usuario_progressBar);
    }

    private void initButtons(){
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                loginUsuarioPresenter.getUsuarioLogin(etEmail.getText().toString(),etPassword.getText().toString());
                etEmail.setText("");
                etPassword.setText("");
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void success(Usuario usuario) {
        progressBar.setVisibility(View.GONE);

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("Id",usuario.getId_usuario());
        editor.putString("Nombre",usuario.getNombre());
        editor.putString("Apellidos",usuario.getApellidos());
        editor.putString("Email",usuario.getEmail());
        editor.putString("Password",usuario.getPassword());
        editor.putBoolean("Registrado",true);
        editor.apply();

        LogedUsuarioFragment logedUsuarioFragment = LogedUsuarioFragment.newInstance(usuario.getNombre(),usuario.getApellidos(),usuario.getEmail(),usuario.getPassword(),usuario.getId_usuario());
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_home_layout,logedUsuarioFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void error(String message) {
        progressBar.setVisibility(View.GONE);
        etEmail.setError("Error");
    }
}
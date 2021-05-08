package com.example.examen2.peliculas.buscarPeliculas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examen2.R;
import com.example.examen2.adapter.PeliculaAdapter;
import com.example.examen2.beans.Genero;
import com.example.examen2.beans.Pelicula;
import com.example.examen2.peliculas.lstGeneros.LstGeneroContract;
import com.example.examen2.peliculas.lstGeneros.LstGeneroPresenter;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BuscarPeliculasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuscarPeliculasFragment extends Fragment implements LstGeneroContract.View, BuscarPeliculasContract.View {

    private ArrayList<String> lstGeneros = new ArrayList<>();
    private LstGeneroPresenter presenter;
    private BuscarPeliculasPresenter buscarPeliculasPresenter;
    private AutoCompleteTextView editTextFilledExposedDropdown;
    private RecyclerView recycler;
    private EditText editTextBuscar;
    private Button buttonBuscar;
    private ProgressBar progressBar;
    private TextView textError;
    private View layoutError;
    private Button retry;


    public BuscarPeliculasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BuscarPeliculasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuscarPeliculasFragment newInstance() {
        BuscarPeliculasFragment fragment = new BuscarPeliculasFragment();
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
        View v = inflater.inflate(R.layout.fragment_buscar_peliculas, container, false);

        presenter = new LstGeneroPresenter(this);
        presenter.getGeneros();

        buscarPeliculasPresenter = new BuscarPeliculasPresenter(this);

        initComponents(v);
        initButtons();

        return v;
    }

    private void initComponents(View v){
        recycler = v.findViewById(R.id.fragment_buscar_peliculas_recycler);
        editTextFilledExposedDropdown = v.findViewById(R.id.filled_exposed_dropdown);
        editTextBuscar = v.findViewById(R.id.fragment_buscar_peliculas_textInput_Buscar);
        buttonBuscar = v.findViewById(R.id.fragment_buscar_peliculas_button_buscar);
        progressBar = v.findViewById(R.id.fragment_buscar_peliculas_progressBar);
        textError = v.findViewById(R.id.fragment_buscar_peliculas_tv_error);
        layoutError = v.findViewById(R.id.fragment_buscar_peliculas_linearLayout_error);
        retry = v.findViewById(R.id.fragment_buscar_peliculas_button_retry);
    }

    private void initButtons(){
        editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genero = String.valueOf(editTextFilledExposedDropdown.getText());
                if (genero.equals("Género")) return;
                progressBar.setVisibility(View.VISIBLE);

                buscarPeliculasPresenter.getPeliculasGenero(genero);
            }
        });

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                editTextBuscar.setText("");

                buscarPeliculasPresenter.getPeliculasSinopsis(String.valueOf(editTextBuscar.getText()));
            }
        });

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                layoutError.setVisibility(View.GONE);

                buscarPeliculasPresenter.getPeliculasGenero(String.valueOf(editTextFilledExposedDropdown.getText()));
            }
        });
    }

    @Override
    public void success(ArrayList<Genero> generos) {
        lstGeneros.add("Género");
        for (int i=0;i<generos.size();i++){
            lstGeneros.add(generos.get(i).getGenero());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.dropdown_menu_popup_item,lstGeneros);
        editTextFilledExposedDropdown.setAdapter(arrayAdapter);
        editTextFilledExposedDropdown.setSelected(false);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void error(String message) {
        Toast.makeText(getContext(),"Fallo al cargar spinner",Toast.LENGTH_LONG).show();
    }

    @Override
    public void successBuscar(ArrayList<Pelicula> peliculas) {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        PeliculaAdapter adapter = new PeliculaAdapter(peliculas);
        recycler.setAdapter(adapter);
        hideError();
    }

    @Override
    public void errorBuscar(String error) {
        showError(error);
    }

    private void hideError(){
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.VISIBLE);
        layoutError.setVisibility(View.GONE);
    }

    private void showError(String error){
        progressBar.setVisibility(View.GONE);
        recycler.setVisibility(View.GONE);
        layoutError.setVisibility(View.VISIBLE);
        textError.setText(error);
    }
}
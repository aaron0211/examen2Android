package com.example.examen2.peliculas.lstPeliculas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.examen2.R;
import com.example.examen2.adapter.PeliculaAdapter;
import com.example.examen2.beans.Pelicula;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LstPeliculasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LstPeliculasFragment extends Fragment implements LstPeliculasContract.View {

    private RecyclerView recycler;
    private LstPeliculasPresenter lstPeliculasPresenter;
    private ProgressBar progressBar;
    private TextView textError;
    private View layoutError;
    private Button retry;

    public LstPeliculasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LstPeliculasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LstPeliculasFragment newInstance() {
        LstPeliculasFragment fragment = new LstPeliculasFragment();
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
        View v = inflater.inflate(R.layout.fragment_lst_peliculas, container, false);

        initComponents(v);
        progressBar.setVisibility(View.VISIBLE);
        lstPeliculasPresenter = new LstPeliculasPresenter(this);
        lstPeliculasPresenter.getPeliculas();
        initButton();

        return v;
    }

    private void initComponents(View v){
        recycler = v.findViewById(R.id.fragment_lst_peliculas_recycler);
        progressBar = v.findViewById(R.id.fragment_lst_peliculas_progressBar);
        textError = v.findViewById(R.id.fragment_lst_peliculas_tv_error);
        layoutError = v.findViewById(R.id.fragment_lst_peliculas_linearLayout_error);
        retry = v.findViewById(R.id.fragment_lst_peliculas_button_retry);
    }

    private void initButton(){
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                layoutError.setVisibility(View.GONE);

                lstPeliculasPresenter.getPeliculas();
            }
        });
    }

    @Override
    public void success(ArrayList<Pelicula> peliculas) {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(getContext(),2));
        PeliculaAdapter adapter = new PeliculaAdapter(peliculas);
        recycler.setAdapter(adapter);
        hideError();

    }

    @Override
    public void error(String message) {
        showError(message);
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
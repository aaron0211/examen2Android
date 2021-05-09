package com.example.examen2.cine.lstCines;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.examen2.R;
import com.example.examen2.adapter.CineAdapter;
import com.example.examen2.adapter.PeliculaAdapter;
import com.example.examen2.beans.Cine;
import com.example.examen2.peliculas.lstPeliculas.LstPeliculasPresenter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LstCinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LstCinesFragment extends Fragment implements LstCinesContract.View {


    private RecyclerView recycler;
    private LstCinesPresenter lstCinesPresenter;
    private ProgressBar progressBar;
    private TextView textError;
    private View layoutError;
    private Button retry;

    public LstCinesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LstCinesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LstCinesFragment newInstance() {
        LstCinesFragment fragment = new LstCinesFragment();
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
        View v = inflater.inflate(R.layout.fragment_lst_cines, container, false);

        initComponents(v);
        progressBar.setVisibility(View.VISIBLE);
        lstCinesPresenter = new LstCinesPresenter(this);
        lstCinesPresenter.getCines();

        return v;
    }

    private void initComponents(View v){
        recycler = v.findViewById(R.id.fragment_lst_cines_recycler);
        progressBar = v.findViewById(R.id.fragment_lst_cines_progressBar);
        textError = v.findViewById(R.id.fragment_lst_cines_tv_error);
        layoutError = v.findViewById(R.id.fragment_lst_cines_linearLayout_error);
        retry = v.findViewById(R.id.fragment_lst_cines_button_retry);
    }

    @Override
    public void success(ArrayList<Cine> cines) {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        CineAdapter adapter = new CineAdapter(cines);
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
package com.example.examen2.peliculas.fdPelicula;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examen2.R;
import com.example.examen2.adapter.CineSesionAdapter;
import com.example.examen2.adapter.PeliculaAdapter;
import com.example.examen2.beans.Cine;
import com.example.examen2.beans.Sesion;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cine_Sesion_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cine_Sesion_Fragment extends Fragment implements FdPeliculaContract.View {

    private static final String ARG_EXTRA_IDPELICULA = "ARG_EXTRA_IDPELICULA";

    private int mId;

    private FdPeliculaPresenter presenter;
    private RecyclerView recyclerView;

    public Cine_Sesion_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id_pelicula Parameter 1.
     * @return A new instance of fragment Cine_Sesion_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Cine_Sesion_Fragment newInstance(int id_pelicula) {
        Cine_Sesion_Fragment fragment = new Cine_Sesion_Fragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EXTRA_IDPELICULA, id_pelicula);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(ARG_EXTRA_IDPELICULA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cine__sesion_, container, false);

        presenter = new FdPeliculaPresenter(this);
        presenter.getCines(mId);

        recyclerView = v.findViewById(R.id.fragment_cine_sesion_recycler);

        return v;
    }

    @Override
    public void success(ArrayList<Sesion> sesions) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        CineSesionAdapter adapter = new CineSesionAdapter(sesions);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void error(String message) {

    }
}
package com.example.examen2.usuarios.comprasUsuario;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examen2.R;
import com.example.examen2.adapter.CompraAdapter;
import com.example.examen2.beans.Compra;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComprasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComprasFragment extends Fragment implements ComprasUsuarioContract.View {

    private static final String ARG_EXTRA_ID = "ARG_EXTRA_ID";

    private int mId;

    private ComprasUsuarioPresenter presenter;
    private RecyclerView recycler;

    public ComprasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id Parameter 1.
     * @return A new instance of fragment ComprasFragment.
     */
    public static ComprasFragment newInstance(int id) {
        ComprasFragment fragment = new ComprasFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_EXTRA_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mId = getArguments().getInt(ARG_EXTRA_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_compras, container, false);

        recycler = v.findViewById(R.id.fragment_compras_recycler);

        presenter = new ComprasUsuarioPresenter(this);
        presenter.getCompras(mId);

        return v;
    }

    @Override
    public void success(ArrayList<Compra> compras) {
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        CompraAdapter adapter = new CompraAdapter(compras);
        recycler.setAdapter(adapter);
    }

    @Override
    public void error(String message) {

    }
}
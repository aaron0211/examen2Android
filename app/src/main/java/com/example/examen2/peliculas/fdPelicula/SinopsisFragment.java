package com.example.examen2.peliculas.fdPelicula;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.examen2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SinopsisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SinopsisFragment extends Fragment {

    private static final String ARG_EXTRA_SINOPSIS = "ARG_EXTRA_SINOPSIS";
    private static final String ARG_EXTRA_FECHA = "ARG_EXTRA_FECHA";

    private String mSinopsis;
    private String mFecha;

    private TextView tvSinopsis,tvFecha;

    public SinopsisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sinopsis Parameter 1.
     * @param fecha Parameter 2.
     * @return A new instance of fragment SinopsisFragment.
     */
    public static SinopsisFragment newInstance(String sinopsis, String fecha) {
        SinopsisFragment fragment = new SinopsisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_EXTRA_SINOPSIS, sinopsis);
        args.putString(ARG_EXTRA_FECHA, fecha);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSinopsis = getArguments().getString(ARG_EXTRA_SINOPSIS);
            mFecha = getArguments().getString(ARG_EXTRA_FECHA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sinopsis, container, false);

        initComponents(v);

        tvSinopsis.setText(mSinopsis);
        tvFecha.setText(mFecha);

        return v;
    }

    private void initComponents(View v){
        tvSinopsis = v.findViewById(R.id.fragment_sinopsis_textView_sinopsis);
        tvFecha = v.findViewById(R.id.fragment_sinopsis_textView_fecha);
    }
}
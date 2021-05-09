package com.example.examen2.cine.lstCines;

import com.example.examen2.beans.Cine;

import java.util.ArrayList;

public class LstCinesPresenter implements LstCinesContract.Presenter{

    private LstCinesContract.View view;
    private LstCinesModel lstCinesModel;

    public LstCinesPresenter(LstCinesContract.View view){
        this.view = view;
        this.lstCinesModel = new LstCinesModel();
    }

    @Override
    public void getCines() {
        lstCinesModel.getCinesWS(new LstCinesContract.Model.OnLstCinesListener() {
            @Override
            public void resolve(ArrayList<Cine> cines) {
                view.success(cines);
            }

            @Override
            public void reject(String error) {
                view.error(error);
            }
        });
    }
}

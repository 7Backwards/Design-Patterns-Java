package com.es2.designpatterns;

import java.util.ArrayList;

public class Container extends Transporte {

    private ArrayList<Transporte> mTranportes = new ArrayList<>();

    public void addTransporte(Transporte transporte) {

        mTranportes.add(transporte);

    }


    @Override
    public void getTranporteItems() {
        System.out.println(getName());
        for (Transporte transporte : mTranportes) {
            transporte.getTranporteItems();
        }
    }
}

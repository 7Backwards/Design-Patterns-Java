package com.es2.designpatterns;

import com.es2.designpatterns.exceptions.FaturaTransporteNotFoundException;

import java.util.HashMap;

public class FaturaTransporte {

    protected HashMap<Integer, FaturaTransporteInterface> mFaturasTransportes;

    public FaturaTransporte() {
        this.mFaturasTransportes = new HashMap<>();
    }

    public int addFatura(FaturaTransporteInterface fatura) {
        int id = this.mFaturasTransportes.size() + 1;
        this.mFaturasTransportes.put(id, fatura);
        return id;
    }

    public String getFatura(int idFatura, Integer idFaturaTransporte) throws FaturaTransporteNotFoundException {
        if (this.mFaturasTransportes.containsKey(idFatura))
            return this.mFaturasTransportes.get(idFatura).getFaturaTransporte(idFaturaTransporte);

        return null;
    }


}

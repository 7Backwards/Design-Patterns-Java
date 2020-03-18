package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Transporte;
import com.es2.designpatterns.exceptions.FaturaTransporteNotFoundException;
import com.es2.designpatterns.exceptions.InvalidPercentagemException;

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

    public void setFatura(int idFatura, Transporte transporte) throws FaturaTransporteNotFoundException, InvalidPercentagemException {
        if (this.mFaturasTransportes.containsKey(idFatura)) {
            this.mFaturasTransportes.get(idFatura).setFaturaTransporte(transporte);
        }
        else
            throw new FaturaTransporteNotFoundException();
    }


    public String getDescritivoFatura(int idFatura) throws FaturaTransporteNotFoundException {

        if (this.mFaturasTransportes.containsKey(idFatura))
            return this.mFaturasTransportes.get(idFatura).getFaturaTransporte();

        throw new FaturaTransporteNotFoundException();
    }

    public void setPercentagem(int idFatura, Float percentagem) throws FaturaTransporteNotFoundException, InvalidPercentagemException {
        if (this.mFaturasTransportes.containsKey(idFatura)) {
            this.mFaturasTransportes.get(idFatura).setPercentagem(percentagem);
        }
        else
            throw new FaturaTransporteNotFoundException();
    }

    public Float getPercentagem(int idFatura) throws FaturaTransporteNotFoundException, InvalidPercentagemException {
        if (this.mFaturasTransportes.containsKey(idFatura))
            return this.mFaturasTransportes.get(idFatura).getPercentagem();
        else
            throw new FaturaTransporteNotFoundException();
    }

}

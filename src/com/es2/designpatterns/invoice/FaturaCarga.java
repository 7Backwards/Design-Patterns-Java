package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;
import com.es2.designpatterns.exceptions.FaturaCargaNotFoundException;
import com.es2.designpatterns.exceptions.InvalidPercentagemException;

import java.util.HashMap;

public class FaturaCarga {

    protected HashMap<Integer, FaturaCargaInterface> mFaturasCargas;


    public FaturaCarga() {
        this.mFaturasCargas = new HashMap<>();
    }


    public int addFatura(FaturaCargaInterface fatura) {

        int id = this.mFaturasCargas.size() + 1;
        this.mFaturasCargas.put(id, fatura);
        return id;
    }

    public void setFatura(int idFatura, Carga carga) throws FaturaCargaNotFoundException, InvalidPercentagemException {
        if (this.mFaturasCargas.containsKey(idFatura)) {
            this.mFaturasCargas.get(idFatura).setFaturaCarga(carga);
        } else
            throw new FaturaCargaNotFoundException();
    }


    public String getDescritivoFatura(int idFatura) throws FaturaCargaNotFoundException {

        if (this.mFaturasCargas.containsKey(idFatura))
            return this.mFaturasCargas.get(idFatura).getFaturaCarga();

        throw new FaturaCargaNotFoundException();
    }

    public void setPercentagem(int idFatura, Float percentagem) throws FaturaCargaNotFoundException, InvalidPercentagemException {
        if (this.mFaturasCargas.containsKey(idFatura)) {
            this.mFaturasCargas.get(idFatura).setPercentagem(percentagem);
        } else
            throw new FaturaCargaNotFoundException();
    }

    public Float getPercentagem(int idFatura) throws FaturaCargaNotFoundException, InvalidPercentagemException {
        if (this.mFaturasCargas.containsKey(idFatura))
            return this.mFaturasCargas.get(idFatura).getPercentagem();
        else
            throw new FaturaCargaNotFoundException();
    }

}

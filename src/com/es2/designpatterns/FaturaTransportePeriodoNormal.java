package com.es2.designpatterns;

import com.es2.designpatterns.exceptions.FaturaTransporteNotFoundException;
import com.es2.designpatterns.exceptions.NullPercentagemException;

import java.util.LinkedHashMap;

public class FaturaTransportePeriodoNormal implements FaturaTransporteInterface {


    protected LinkedHashMap<Integer, String> mFaturas;
    protected Float valorPercentagem;

    public FaturaTransportePeriodoNormal() {

        mFaturas = new LinkedHashMap<>();
    }

    @Override
    public void setFaturaTransporte(Transporte transporte) throws NullPercentagemException {
        if(valorPercentagem > 0)
            mFaturas.put(mFaturas.size() + 1, transporte.getTransporteItems() +
                    "\nValor Total da Carga - " + transporte.getTransporteTotalPrice() +
                    "\nValor do Transporte - " + transporte.getTransporteTotalPrice() * (valorPercentagem / 100) +
                    "\nPercentagem - " + valorPercentagem +
                    "\nTipo de Transporte - " + this.getClass());
        else
            throw new NullPercentagemException();
    }

    @Override
    public String getFaturaTransporte(Integer idFaturaTransporte) throws FaturaTransporteNotFoundException {
        if (mFaturas.containsKey(idFaturaTransporte))
            return mFaturas.get(idFaturaTransporte);
        else
            throw new FaturaTransporteNotFoundException();
    }

    @Override
    public void setPercentagem(Float percentagem) {
        this.valorPercentagem = percentagem;
    }

    @Override
    public Float getPercentagem() throws NullPercentagemException {
        if (valorPercentagem > 0)
            return valorPercentagem;
        else
            throw new NullPercentagemException();
    }
}

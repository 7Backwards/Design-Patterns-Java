package com.es2.designpatterns;

import com.es2.designpatterns.exceptions.FaturaTransporteNotFoundException;
import com.es2.designpatterns.exceptions.NullPercentagemException;

public interface FaturaTransporteInterface {

    void setFaturaTransporte(Transporte transporte) throws NullPercentagemException;
    String getFaturaTransporte(Integer idFaturaTransporte) throws FaturaTransporteNotFoundException;
    void setPercentagem(Float percentagem);
    Float getPercentagem() throws NullPercentagemException;
}

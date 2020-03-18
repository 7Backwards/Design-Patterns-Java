package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Transporte;
import com.es2.designpatterns.exceptions.FaturaTransporteNotFoundException;
import com.es2.designpatterns.exceptions.InvalidPercentagemException;

public interface FaturaTransporteInterface {

    void setFaturaTransporte(Transporte transporte) throws InvalidPercentagemException;
    String getFaturaTransporte() throws FaturaTransporteNotFoundException;
    void setPercentagem(Float percentagem) throws InvalidPercentagemException;
    Float getPercentagem() throws InvalidPercentagemException;
}

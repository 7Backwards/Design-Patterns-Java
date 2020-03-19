package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;
import com.es2.designpatterns.exceptions.FaturaCargaNotFoundException;
import com.es2.designpatterns.exceptions.InvalidPercentagemException;

public interface FaturaCargaInterface {

    String getFaturaCarga() throws FaturaCargaNotFoundException;

    void setFaturaCarga(Carga carga) throws InvalidPercentagemException;

    Float getPercentagem() throws InvalidPercentagemException;

    void setPercentagem(Float percentagem) throws InvalidPercentagemException;
}

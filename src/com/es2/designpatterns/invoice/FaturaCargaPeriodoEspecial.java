package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;
import com.es2.designpatterns.exceptions.FaturaCargaNotFoundException;
import com.es2.designpatterns.exceptions.InvalidPercentagemException;

public class FaturaCargaPeriodoEspecial implements FaturaCargaInterface {


    protected String stringFaturas = "";
    protected Float valorPercentagem;

    @Override
    public String getFaturaCarga() throws FaturaCargaNotFoundException {
        if (stringFaturas.length() > 0)
            return stringFaturas;
        else
            throw new FaturaCargaNotFoundException();
    }

    @Override
    public void setFaturaCarga(Carga carga) throws InvalidPercentagemException {
        if (valorPercentagem > 0) {
            stringFaturas = carga.getCargaItems() +
                    "\nValor Total da Carga - " + carga.getCargaTotalPrice() +
                    "\nValor do Carga - " + carga.getCargaTotalPrice() * (valorPercentagem / 100) +
                    "\nPercentagem - " + valorPercentagem +
                    "\nTipo de Carga - " + this.getClass();
        } else {
            throw new InvalidPercentagemException();
        }
    }

    @Override
    public Float getPercentagem() throws InvalidPercentagemException {
        if (valorPercentagem > 0)
            return valorPercentagem;
        else
            throw new InvalidPercentagemException();
    }

    @Override
    public void setPercentagem(Float percentagem) throws InvalidPercentagemException {
        if (percentagem == null || percentagem <= 0)
            throw new InvalidPercentagemException();
        else
            this.valorPercentagem = percentagem;
    }
}

package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Transporte;
import com.es2.designpatterns.exceptions.FaturaTransporteNotFoundException;
import com.es2.designpatterns.exceptions.NullPercentagemException;

public class FaturaTransportePeriodoEspecial implements FaturaTransporteInterface {


    protected String stringFaturas = "";
    protected Float valorPercentagem;

    @Override
    public void setFaturaTransporte(Transporte transporte) throws NullPercentagemException {
        if(valorPercentagem > 0) {
            stringFaturas = transporte.getTransporteItems() +
                    "\nValor Total da Carga - " + transporte.getTransporteTotalPrice() +
                    "\nValor do Transporte - " + transporte.getTransporteTotalPrice() * (valorPercentagem / 100) +
                    "\nPercentagem - " + valorPercentagem +
                    "\nTipo de Transporte - " + this.getClass();
        }
        else {
            throw new NullPercentagemException();
        }
    }

    @Override
    public String getFaturaTransporte() throws FaturaTransporteNotFoundException {
        if (stringFaturas.length() > 0)
            return stringFaturas;
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

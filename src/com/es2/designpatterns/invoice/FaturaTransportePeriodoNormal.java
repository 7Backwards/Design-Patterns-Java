package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Transporte;
import com.es2.designpatterns.exceptions.FaturaTransporteNotFoundException;
import com.es2.designpatterns.exceptions.InvalidPercentagemException;

public class FaturaTransportePeriodoNormal implements FaturaTransporteInterface {


    protected String stringFaturas = "";
    protected Float valorPercentagem;

    @Override
    public void setFaturaTransporte(Transporte transporte) throws InvalidPercentagemException {
        if(valorPercentagem > 0) {
            stringFaturas = transporte.getTransporteItems() +
                    "\nValor Total da Carga - " + transporte.getTransporteTotalPrice() +
                    "\nValor do Transporte - " + transporte.getTransporteTotalPrice() * (valorPercentagem / 100) +
                    "\nPercentagem - " + valorPercentagem +
                    "\nTipo de Transporte - " + this.getClass();
        }
        else {
            throw new InvalidPercentagemException();
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
    public void setPercentagem(Float percentagem) throws InvalidPercentagemException {
        if (percentagem != null)
            this.valorPercentagem = percentagem;
        else
            throw new InvalidPercentagemException();
    }

    @Override
    public Float getPercentagem() throws InvalidPercentagemException {
        if (valorPercentagem > 0)
            return valorPercentagem;
        else
            throw new InvalidPercentagemException();
    }
}

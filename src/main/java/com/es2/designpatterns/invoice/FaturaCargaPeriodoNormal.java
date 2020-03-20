package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;

public class FaturaCargaPeriodoNormal implements FaturaCargaInterface {

    Float valorPercentagem = (float)5.0;

    @Override
    public void emiteFaturaCarga(Carga carga) {

        System.out.println(carga.getCargaItems() +
                "\nValor Total da Carga - " + carga.getCargaTotalPrice() +
                "\nValor do Carga - " + carga.getCargaTotalPrice() * (valorPercentagem / 100) +
                "\nPercentagem - " + valorPercentagem +
                "\nTipo de Carga - " + this.getClass());
    }
}

package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;

public class FaturaCargaPeriodoNormal implements FaturaCargaInterface {

    Float valorPercentagem = (float)5.0;

    /**
     *        Fatura período especial - 5%
     *
     * @param carga Container composite
     */
    @Override
    public void emiteFaturaCarga(Carga carga) {

        System.out.println(carga.getCargaItems() +
                "\nValor Total da Carga - " + carga.getCargaTotalPrice() +
                "\nValor Faturado - " + carga.getCargaTotalPrice() * (valorPercentagem / 100) +
                "\nPercentagem - " + valorPercentagem +
                "\nTipo de Carga - " + this.getClass());
    }
}

package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;

public class FaturaCargaPeriodoEspecial implements FaturaCargaInterface {

    private Float valorPercentagem = (float) 10.0;

    /**
     *        Fatura período especial - 10%
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

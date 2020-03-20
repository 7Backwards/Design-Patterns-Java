package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;

public abstract class FaturaCarga {

    protected FaturaCargaInterface emiteFaturaCarga;

    protected FaturaCarga(FaturaCargaInterface faturaCargaInterface) {
        this.emiteFaturaCarga = faturaCargaInterface;
    }

    public abstract void emite(Carga carga);

}

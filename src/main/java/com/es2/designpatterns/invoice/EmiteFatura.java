package com.es2.designpatterns.invoice;


import com.es2.designpatterns.cargo.Carga;

public class EmiteFatura extends FaturaCarga {

    private FaturaCargaInterface faturaCargaInterface;

    public EmiteFatura(FaturaCargaInterface faturaCargaInterface) {
        super(faturaCargaInterface);

        this.faturaCargaInterface = faturaCargaInterface;
    }

    @Override
    public void emite(Carga carga) {
        this.faturaCargaInterface.emiteFaturaCarga(carga);
    }
}

package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;
import com.es2.designpatterns.exceptions.FaturaTypeNotFoundException;

import java.util.HashMap;

public abstract class FaturaCarga {

    protected HashMap<String, FaturaCargaInterface> mEmiteFaturaCarga = new HashMap<>();

    /**
     * Abstract class containing FaturaCargaInterface aggregation
     * For simplicity interfaces added once
     */
    protected FaturaCarga() {
        mEmiteFaturaCarga.put("normal", new FaturaCargaPeriodoNormal());
        mEmiteFaturaCarga.put("especial", new FaturaCargaPeriodoEspecial());
    }

    public abstract void emite(Carga carga, String tipoFatura) throws FaturaTypeNotFoundException;
}

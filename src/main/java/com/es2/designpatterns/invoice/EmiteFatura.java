package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Carga;
import com.es2.designpatterns.exceptions.FaturaTypeNotFoundException;

public class EmiteFatura extends FaturaCarga {

    public EmiteFatura() {
        super();
    }

    /**
     *
     * @param carga Container composite
     * @param tipoFatura Fatura type to be emitted
     */
    @Override
    public void emite(Carga carga, String tipoFatura) throws FaturaTypeNotFoundException {
        if("normal".equals(tipoFatura)) {
            mEmiteFaturaCarga.get(tipoFatura).emiteFaturaCarga(carga);
        }
        else if("especial".equals(tipoFatura)) {
            mEmiteFaturaCarga.get(tipoFatura).emiteFaturaCarga(carga);
        }
        else
            throw new FaturaTypeNotFoundException();
    }
}

package com.es2.designpatterns.cargo;

import com.es2.designpatterns.exceptions.ContainerFullException;
import java.util.ArrayList;

public class Container extends Carga {

    private ArrayList<Carga> mCargas = new ArrayList<>();

    public void addCarga(Carga carga) throws ContainerFullException {

        if (this.getSize() - carga.getSize() >= 0) {
            this.setSize(this.getSize() - carga.getSize());
            mCargas.add(carga);
        }
        else
            throw new ContainerFullException();
    }

    @Override
    public String getCargaItems() {

        StringBuilder response = new StringBuilder();

        response.append(getName()).append("\n");
        for (Carga carga : mCargas) {
            response.append(carga.getCargaItems());
        }
        return response.toString();
    }

    @Override
    public float getCargaTotalPrice() {
        float totalPrice = 0;
        for (Carga carga : mCargas) {
            totalPrice += carga.getCargaTotalPrice();
        }
        return totalPrice;
    }

    @Override
    public void dropCarga() {
        for (Carga carga : mCargas) {
            carga.dropCarga();
        }
        mCargas.clear();
    }
}

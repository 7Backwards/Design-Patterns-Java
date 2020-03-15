package com.es2.designpatterns.cargo;

import com.es2.designpatterns.exceptions.ContainerFullException;

import java.util.ArrayList;

public class Container extends Transporte {

    private ArrayList<Transporte> mTransportes = new ArrayList<>();

    public void addTransporte(Transporte transporte) throws ContainerFullException {

        if (this.getSize() - transporte.getSize() >= 0)
            mTransportes.add(transporte);
        else
            throw new ContainerFullException();
    }


    @Override
    public String getTransporteItems() {

        StringBuilder response = new StringBuilder();

        //System.out.print(getName());
        response.append(getName()).append("\n");
        for (Transporte transporte : mTransportes) {
            response.append(transporte.getTransporteItems());
        }
        return response.toString();
    }

    @Override
    public float getTransporteTotalPrice() {
        float totalPrice = 0;
        for (Transporte transporte : mTransportes) {
            totalPrice += transporte.getTransporteTotalPrice();
        }
        return totalPrice;
    }
}

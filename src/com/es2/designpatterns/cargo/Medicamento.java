package com.es2.designpatterns.cargo;

public class Medicamento extends Transporte {

    private int quantity;
    private float unitValue;

    public float getMedicineValue() {
        return unitValue * quantity;
    }

    public void setUnitValue(float unitValue) {
        this.unitValue = unitValue;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public String getTransporteItems() {

        return getName() + " -> Value: " + getMedicineValue() + "\n";
    }

    @Override
    public float getTransporteTotalPrice() {
        return getMedicineValue();
    }
}
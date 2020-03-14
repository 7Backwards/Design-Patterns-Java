package com.es2.designpatterns;

public class Medicine extends Transporte {

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
    public void getTranporteItems() {
        System.out.println(getName() + " -> Value: " + getMedicineValue());
    }
}

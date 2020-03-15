package com.es2.designpatterns.cargo;

import com.es2.designpatterns.users.User;

public abstract class Transporte {

    private String name;
    private int size;
    private User motoristaInCharge;

    public Transporte() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public User getMotoristaInCharge() {
        return motoristaInCharge;
    }

    public void setMotoristaInCharge(User motoristaInCharge) {
        this.motoristaInCharge = motoristaInCharge;
    }

    public abstract String getTransporteItems();

    public abstract float getTransporteTotalPrice();
}

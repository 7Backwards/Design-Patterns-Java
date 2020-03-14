package com.es2.designpatterns;

public abstract class Transporte {

    private String name;
    private int size;

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

    public abstract void getTranporteItems();
}

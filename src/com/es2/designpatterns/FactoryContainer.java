package com.es2.designpatterns;

import com.es2.designpatterns.exceptions.ContainerTypeNotFoundException;

public abstract class FactoryContainer {

    /**
     *
     */
    public FactoryContainer() {

    }



    public static Transporte makeContainer(String type) throws ContainerTypeNotFoundException {

        switch (type) {
            case "Container":
                return new Container();
            case "Medicamento":
                return new Medicamento();
            default:
                throw new ContainerTypeNotFoundException();
        }
    }
}

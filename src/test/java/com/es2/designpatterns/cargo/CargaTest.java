package com.es2.designpatterns.cargo;

import com.es2.designpatterns.exceptions.*;
import com.es2.designpatterns.users.UserManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CargaTest {

    @Test
    void testCarga() throws ContainerNotFoundException, UserNotFoundException, ContainerFullException, UserTypeNotFoundException, UserExistingException, ContainerPoolMaxedOutException {

        //Create Contentor
        ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
        Container Cargo = ContainerReusablePool.getInstance().getContainer("Contentor");
        Cargo.setName("Cargo-1");
        UserManager.getInstanceLogin().registerUser("Motorista", "moto1", "moto1");
        Cargo.setMotoristaInCharge(UserManager.getInstanceLogin().getAvailableMotorista());

        //Create Caixa
        ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 10);
        Container caixa1 = ContainerReusablePool.getInstance().getContainer("Caixa");
        caixa1.setName("Caixa-1");

        //Create Embalagem
        ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 1);
        Container embalagem1 = ContainerReusablePool.getInstance().getContainer("Embalagem");
        embalagem1.setName("Embalagem-1");

        //Create Aspirina
        Medicamento medicamento1 = new Medicamento();
        medicamento1.setName("Aspirina");
        medicamento1.setSize(1);
        medicamento1.setQuantity(10);
        medicamento1.setUnitValue(5);

        //Create Carga
        Cargo.addCarga(caixa1);
        caixa1.addCarga(embalagem1);
        embalagem1.addCarga(medicamento1);

        ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "Cargo-1");
        ContainerReusablePool.getInstance().releaseContainerByName("Caixa", "Caixa-1");
        ContainerReusablePool.getInstance().releaseContainerByName("Embalagem", "Embalagem-1");
    }

    @Test
    void testContainerFullException() {

        assertThrows(ContainerFullException.class,
                () -> {

                    //Create Caixa
                    ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 10);
                    Container container = ContainerReusablePool.getInstance().getContainer("Caixa");
                    container.setName("Caixa3");

                    //Create Medicamento
                    Medicamento medicamento3 = new Medicamento();
                    medicamento3.setName("Brufen");
                    medicamento3.setSize(11);
                    medicamento3.setQuantity(1);
                    medicamento3.setUnitValue(7);

                    //Add medicamento(size = 11) to Caixa(size = 10)
                    container.addCarga(medicamento3);

                });
    }
}
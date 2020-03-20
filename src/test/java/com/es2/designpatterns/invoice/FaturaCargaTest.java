package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Container;
import com.es2.designpatterns.cargo.ContainerReusablePool;
import com.es2.designpatterns.cargo.Medicamento;
import com.es2.designpatterns.exceptions.*;
import com.es2.designpatterns.users.UserManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class FaturaCargaTest {

    @Test
    void testCreateFatura() throws FaturaCargaNotFoundException, ContainerFullException, ContainerNotFoundException, UserNotFoundException, ContainerPoolMaxedOutException, UserTypeNotFoundException, UserExistingException, InvalidPercentagemException {

        ContainerReusablePool.getInstance().resetContainers();
        //Add Contentor
        ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
        Container Cargo = ContainerReusablePool.getInstance().getContainer("Contentor");
        Cargo.setName("Cargo-1");

        //Create Motorista
        UserManager.getInstanceLogin().registerUser("Motorista", "moto4", "moto4");
        Cargo.setMotoristaInCharge(UserManager.getInstanceLogin().getAvailableMotorista());

        //Add Caixa
        ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 10);
        Container caixa = ContainerReusablePool.getInstance().getContainer("Caixa");
        caixa.setName("Caixa-1");

        //Add Embalagem
        ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 10);
        Container embalagem1 = ContainerReusablePool.getInstance().getContainer("Embalagem");
        embalagem1.setName("Embalagem-1");

        //Add Medicamento
        Medicamento medicamento1 = new Medicamento();
        medicamento1.setName("Aspirina");
        medicamento1.setSize(1);
        medicamento1.setQuantity(10);
        medicamento1.setUnitValue(5);

        //Add Carga
        Cargo.addCarga(caixa);
        caixa.addCarga(embalagem1);
        embalagem1.addCarga(medicamento1);

        //Test create Fatura

        FaturaCarga faturaCarga = new FaturaCarga();
        int idFatura = faturaCarga.addFatura(new FaturaCargaPeriodoNormal());
        faturaCarga.setPercentagem(idFatura, (float) 50);
        faturaCarga.setFatura(idFatura, Cargo);
        ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "Cargo-1");
    }


    @Test
    void testFaturaCargaNotFoundException() {

        assertThrows(FaturaCargaNotFoundException.class,
                () -> {

                    FaturaCarga faturaCarga = new FaturaCarga();
                    faturaCarga.setPercentagem(2135, (float) 50);
                });
    }

    @Test
    void testInvalidPercentagemException() {

        assertThrows(InvalidPercentagemException.class,
                () -> {
                    ContainerReusablePool.getInstance().resetContainers();
                    //Add Contentor
                    ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
                    Container Cargo2 = ContainerReusablePool.getInstance().getContainer("Contentor");
                    Cargo2.setName("Cargo-2");
                    //Create Motorista
                    UserManager.getInstanceLogin().registerUser("Motorista", "moto2", "moto2");
                    Cargo2.setMotoristaInCharge(UserManager.getInstanceLogin().getAvailableMotorista());

                    //Add Caixa
                    ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 10);
                    Container caixa2 = ContainerReusablePool.getInstance().getContainer("Caixa");
                    caixa2.setName("Caixa-2");

                    //Add Embalagem
                    ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 10);
                    Container embalagem2 = ContainerReusablePool.getInstance().getContainer("Embalagem");
                    embalagem2.setName("Embalagem-2");

                    //Add Medicamento
                    Medicamento medicamento2 = new Medicamento();
                    medicamento2.setName("Aspirina");
                    medicamento2.setSize(1);
                    medicamento2.setQuantity(10);
                    medicamento2.setUnitValue(5);

                    //Add Carga
                    Cargo2.addCarga(caixa2);
                    caixa2.addCarga(embalagem2);
                    embalagem2.addCarga(medicamento2);

                    //Test create Fatura

                    FaturaCarga faturaCarga = new FaturaCarga();
                    int idFatura = faturaCarga.addFatura(new FaturaCargaPeriodoNormal());
                    faturaCarga.setPercentagem(idFatura, null);
                    ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "Cargo-2");
                });
    }

}

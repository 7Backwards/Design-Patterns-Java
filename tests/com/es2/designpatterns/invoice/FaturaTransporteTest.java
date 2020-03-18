package com.es2.designpatterns.invoice;

import com.es2.designpatterns.cargo.Container;
import com.es2.designpatterns.cargo.ContainerReusablePool;
import com.es2.designpatterns.cargo.Medicamento;
import com.es2.designpatterns.exceptions.*;
import com.es2.designpatterns.users.UserManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FaturaTransporteTest {

    @Test
    void testCreateFatura() throws FaturaTransporteNotFoundException, ContainerFullException, ContainerNotFoundException, UserNotFoundException, ContainerPoolMaxedOutException, UserTypeNotFoundException, UserExistingException, InvalidPercentagemException {

        //Add Contentor
        ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
        Container Cargo = ContainerReusablePool.getInstance().getContainer("Contentor");
        Cargo.setName("Cargo-1");
        
        //Create Motorista
        UserManager.getInstanceLogin().registerUser("Motorista", "moto1", "moto1");
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

        //Add Transporte
        Cargo.addTransporte(caixa);
        caixa.addTransporte(embalagem1);
        embalagem1.addTransporte(medicamento1);

        //Test create Fatura

        FaturaTransporte faturaTransporte = new FaturaTransporte();
        int idFatura = faturaTransporte.addFatura(new FaturaTransportePeriodoNormal());
        faturaTransporte.setPercentagem(idFatura, (float)50);
        faturaTransporte.setFatura(idFatura, Cargo);
    }


    @Test
    void testFaturaTransporteNotFoundException() {

        assertThrows(FaturaTransporteNotFoundException.class,
                ()->{

                    FaturaTransporte faturaTransporte = new FaturaTransporte();
                    faturaTransporte.setPercentagem(2135, (float)50);
                });
    }

    @Test
    void testInvalidPercentagemException() {

        assertThrows(InvalidPercentagemException.class,
                ()->{
                    //Add Contentor
                    ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
                    Container Cargo1 = ContainerReusablePool.getInstance().getContainer("Contentor");
                    Cargo1.setName("Cargo-1");
                    //Create Motorista
                    UserManager.getInstanceLogin().registerUser("Motorista", "moto2", "moto2");
                    Cargo1.setMotoristaInCharge(UserManager.getInstanceLogin().getAvailableMotorista());

                    //Add Caixa
                    ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 10);
                    Container caixa1 = ContainerReusablePool.getInstance().getContainer("Caixa");
                    caixa1.setName("Caixa-1");

                    //Add Embalagem
                    ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 10);
                    Container embalagem11 = ContainerReusablePool.getInstance().getContainer("Embalagem");
                    embalagem11.setName("Embalagem-1");

                    //Add Medicamento
                    Medicamento medicamento11 = new Medicamento();
                    medicamento11.setName("Aspirina");
                    medicamento11.setSize(1);
                    medicamento11.setQuantity(10);
                    medicamento11.setUnitValue(5);

                    //Add Transporte
                    Cargo1.addTransporte(caixa1);
                    caixa1.addTransporte(embalagem11);
                    embalagem11.addTransporte(medicamento11);

                    //Test create Fatura

                    FaturaTransporte faturaTransporte = new FaturaTransporte();
                    int idFatura = faturaTransporte.addFatura(new FaturaTransportePeriodoNormal());
                    faturaTransporte.setPercentagem(idFatura, null);
                     });
                    }

}

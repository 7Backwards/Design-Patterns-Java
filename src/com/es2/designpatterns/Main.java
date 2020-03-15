package com.es2.designpatterns;

import com.es2.designpatterns.cargo.Container;
import com.es2.designpatterns.cargo.ContainerReusablePool;
import com.es2.designpatterns.cargo.Medicamento;
import com.es2.designpatterns.exceptions.*;
import com.es2.designpatterns.invoice.FaturaTransporte;
import com.es2.designpatterns.invoice.FaturaTransportePeriodoNormal;
import com.es2.designpatterns.users.UserManager;

public class Main {


    public static void main(String[] args) throws ContainerFullException {

        System.out.println("Software meDelivery:");

        System.out.print("\nRegisto do Gestor - ");
        System.out.print(UserManager.getInstanceLogin().registerUser("Gestor", "admin", "admin"));

        System.out.print("\nRegisto do Motorista 1 - ");
        System.out.print(UserManager.getInstanceLogin().registerUser("Motorista", "moto1", "moto1"));
        System.out.print("\nRegisto do Motorista 2 - ");
        System.out.print(UserManager.getInstanceLogin().registerUser("Motorista", "moto2", "moto2"));

        System.out.print("\nLogin do Gestor - ");
        try {
            System.out.print(UserManager.getInstanceLogin().loginUser("admin", "admin"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        System.out.print("\nLogin do Motorista Falhado - ");
        try {
            System.out.println(UserManager.getInstanceLogin().loginUser("moto1", "moto2"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        //Adicionar Container objects to ContainerReusablePool
        try {
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 30);
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 30);
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 30);

            ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 5);
            ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 5);
            ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 5);
            ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 5);
            ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 5);
            ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 5);

            ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 1);
            ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 1);
            ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 1);
            ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 1);
            ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 1);

            //Error - container limit 3
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
        } catch (ContainerPoolMaxedOutException | ContainerNotFoundException e) {
            e.printStackTrace();
        }

        //Test get Container -> free container
        try {
            Container container = ContainerReusablePool.getInstance().getContainer("Contentor");
            System.out.println(container.getName());
            container.setName("teste");
            ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "teste");
            //ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "teste");
            //Container container1 = ContainerReusablePool.getInstance().getContainer("Caixa");
            //System.out.println(container1.getName());
        } catch (ContainerNotFoundException e) {
            e.printStackTrace();
        }

        //Test Create Cargo
        try {
            Container Cargo = ContainerReusablePool.getInstance().getContainer("Contentor");
            Cargo.setName("Cargo-1");
            Cargo.setMotoristaInCharge(UserManager.getInstanceLogin().getAvailableMotorista());
            //Add "Containers"
            Container caixa1 = ContainerReusablePool.getInstance().getContainer("Caixa");
            caixa1.setName("Caixa-1");
            Container embalagem1 = ContainerReusablePool.getInstance().getContainer("Embalagem");
            embalagem1.setName("Embalagem-1");
            Medicamento medicamento1 = new Medicamento();
            medicamento1.setName("Aspirina");
            medicamento1.setSize(1);
            medicamento1.setQuantity(10);
            medicamento1.setUnitValue(5);

            Container embalagem2 = ContainerReusablePool.getInstance().getContainer("Embalagem");
            embalagem2.setName("Embalagem-2");
            Medicamento medicamento2 = new Medicamento();
            medicamento2.setName("Gutalax");
            medicamento2.setSize(1);
            medicamento2.setQuantity(1);
            medicamento2.setUnitValue(7);

            Cargo.addTransporte(caixa1);
            caixa1.addTransporte(embalagem1);
            embalagem1.addTransporte(medicamento1);
            caixa1.addTransporte(embalagem2);
            embalagem2.addTransporte(medicamento2);


            System.out.println(Cargo.getTransporteItems());

            System.out.println((Cargo.getTransporteTotalPrice()));


            //Test create invoice
            FaturaTransporte faturaTransporte = new FaturaTransporte();
            int idFatura = faturaTransporte.addFatura(new FaturaTransportePeriodoNormal());
            faturaTransporte.setPercentagem(idFatura, (float)50);
            faturaTransporte.setFatura(idFatura, Cargo);
            //Release Motorista
            UserManager.getInstanceLogin().freeMotorista(Cargo.getMotoristaInCharge().getUsername());
            //Release Containers
            ContainerReusablePool.getInstance().releaseContainerByName("Contentor", "Cargo-1");
            ContainerReusablePool.getInstance().releaseContainerByName("Caixa", "Caixa-1");
            ContainerReusablePool.getInstance().releaseContainerByName("Embalagem", "Embalagem-1");
            ContainerReusablePool.getInstance().releaseContainerByName("Embalagem", "Embalagem-2");

            //Percentagem
            System.out.println(faturaTransporte.getPercentagem(idFatura));
            //Descritivo
            System.out.println(faturaTransporte.getDescritivoFatura(idFatura));

            //Finish
            System.out.println("FINISH");


        } catch (ContainerNotFoundException | UserNotFoundException | FaturaTransporteNotFoundException | NullPercentagemException e) {
            e.printStackTrace();
        }


    }

}



        /*
        //Test composite without restrictions
        Container mainC = new Container();
        Container containerx = new Container();
        Container containery = new Container();
        Container containerz = new Container();
        Medicamento medicamento1 = new Medicamento();
        Medicamento medicamento2 = new Medicamento();
        Medicamento medicamento3 = new Medicamento();

        containerx.setName("A-");
        containery.setName("B-");
        containerz.setName("C-");
        mainC.setSize(10);
        containerx.setSize(9);
        containery.setSize(8);
        containerz.setSize(7);

        mainC.setName("MAINC-");
        medicamento1.setSize(1);
        medicamento1.setName("AAS");
        medicamento1.setQuantity(10);
        medicamento1.setUnitValue(2);
        medicamento2.setSize(2);
        medicamento2.setName("Pantoc");
        medicamento2.setQuantity(1);
        medicamento2.setUnitValue(5);
        medicamento3.setSize(2);
        medicamento3.setName("Diazepam");
        medicamento3.setQuantity(7);
        medicamento3.setUnitValue(5);

        mainC.addTransporte(containerx);
        containerx.addTransporte(containery);
        containery.addTransporte(containerz);
        containerz.addTransporte(medicamento1);
        containerz.addTransporte(medicamento2);
        containerz.addTransporte(medicamento3);

        System.out.println(mainC.getTransporteItems());

        System.out.println((mainC.getTransporteTotalPrice()));
        */
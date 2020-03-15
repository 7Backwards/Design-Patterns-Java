package com.es2.designpatterns;

import com.es2.designpatterns.exceptions.ContainerFullException;
import com.es2.designpatterns.exceptions.ContainerNotFoundException;
import com.es2.designpatterns.exceptions.ContainerPoolMaxedOutException;
import com.es2.designpatterns.exceptions.UserNotFoundException;

public class Main {


    public static void main(String[] args) throws ContainerFullException {

        System.out.println("Software meDelivery:");


        System.out.println("\nRegisto do Gestor - ");
        System.out.print(UserManager.getInstanceLogin().registerUser("Gestor", "admin", "admin"));

        System.out.println("\nRegisto do Motorista - ");
        System.out.print(UserManager.getInstanceLogin().registerUser("Motorista", "moto1", "moto1"));

        System.out.println("\nLogin do Gestor - ");
        try {
            System.out.print(UserManager.getInstanceLogin().loginUser("admin", "admin"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("\nLogin do Motorista Falhado - ");
        try {
            System.out.println(UserManager.getInstanceLogin().loginUser("moto1", "moto2"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }


        try {
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
            ContainerReusablePool.getInstance().addContainer("Container", "Caixa", 5);
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
            ContainerReusablePool.getInstance().addContainer("Container", "Embalagem", 1);
            ContainerReusablePool.getInstance().addContainer("Container", "Contentor", 10);
        } catch (ContainerPoolMaxedOutException | ContainerNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Container container = ContainerReusablePool.getInstance().getContainer("Caixa");
            System.out.println(container.getName());
            //Container container1 = ContainerReusablePool.getInstance().getContainer("Caixa");
            //System.out.println(container1.getName());
        } catch (ContainerNotFoundException e) {
            e.printStackTrace();
        }


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



    //Test


    }
}

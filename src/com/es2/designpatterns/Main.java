package com.es2.designpatterns;

import com.es2.designpatterns.exceptions.UserNotFoundException;

public class Main {


    public static void main(String[] args) {

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
            System.out.print(UserManager.getInstanceLogin().loginUser("moto1", "moto2"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }





    }
}

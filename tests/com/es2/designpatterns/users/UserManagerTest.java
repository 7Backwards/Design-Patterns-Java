package com.es2.designpatterns.users;

import com.es2.designpatterns.exceptions.UserExistingException;
import com.es2.designpatterns.exceptions.UserNotFoundException;

import com.es2.designpatterns.exceptions.UserTypeNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserManagerTest {

    @Test
    void testUserNotFoundException() {

        assertThrows(UserNotFoundException.class,
                ()->{

                    UserManager.getInstanceLogin().loginUser("moto1", "moto2");
                });
    }

    @Test
    void testRegisterUser() throws UserTypeNotFoundException, UserExistingException {

        UserManager.getInstanceLogin().registerUser("Gestor", "admin", "admin");
    }

    @Test
    void testLoginExistingUser() throws UserNotFoundException, UserTypeNotFoundException, UserExistingException {

        //Registar Gestor
        UserManager.getInstanceLogin().registerUser("Gestor", "admin", "admin");
        //Testar login do Gestor registado
        UserManager.getInstanceLogin().loginUser("admin", "admin");
    }

    @Test
    void testUserTypeNotFoundException() {

        assertThrows(UserTypeNotFoundException.class,
                ()->{

                    UserManager.getInstanceLogin().registerUser("fakeType", "admin1", "admin1");
                });
    }

    @Test
    void testUserExistingException() {

        assertThrows(UserExistingException.class,
                ()->{

                    UserManager.getInstanceLogin().registerUser("Gestor", "admin", "admin");
                    UserManager.getInstanceLogin().registerUser("Gestor", "admin", "admin");
                });

    }
}
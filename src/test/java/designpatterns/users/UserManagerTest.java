package designpatterns.users;

import com.es2.designpatterns.exceptions.UserExistingException;
import com.es2.designpatterns.exceptions.UserNotFoundException;
import com.es2.designpatterns.exceptions.UserTypeNotFoundException;
import com.es2.designpatterns.users.UserManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserManagerTest {

    @Test
    void testRegisterUser() throws UserTypeNotFoundException, UserExistingException {

        UserManager.getInstanceLogin().registerUser("Gestor", "admin1", "admin1");
    }

    @Test
    void testUserNotFoundException() {

        assertThrows(UserNotFoundException.class,
                () -> {

                    UserManager.getInstanceLogin().loginUser("moto5", "moto5");
                });
    }

    @Test
    void testLoginExistingUser() throws UserNotFoundException, UserTypeNotFoundException, UserExistingException {

        //Registar Gestor
        UserManager.getInstanceLogin().registerUser("Gestor", "admin2", "admin2");
        //Testar login do Gestor registado
        UserManager.getInstanceLogin().loginUser("admin2", "admin2");
    }

    @Test
    void testUserTypeNotFoundException() {

        assertThrows(UserTypeNotFoundException.class,
                () -> {

                    UserManager.getInstanceLogin().registerUser("fakeType", "admin1", "admin1");
                });
    }

    @Test
    void testUserExistingException() {

        assertThrows(UserExistingException.class,
                () -> {

                    UserManager.getInstanceLogin().registerUser("Gestor", "admin3", "admin3");
                    UserManager.getInstanceLogin().registerUser("Gestor", "admin3", "admin3");
                });

    }
}
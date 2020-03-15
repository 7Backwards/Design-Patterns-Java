package com.es2.designpatterns;


import com.es2.designpatterns.exceptions.UserNotFoundException;
import org.junit.Test;

    public class LoginTest {
        @Test(expected = UserNotFoundException.class)
        public void testServiceNotFoundExceptionThrown() throws UserNotFoundException {
            UserManager.getInstanceLogin().loginUser("admin", "admin");
        }

}

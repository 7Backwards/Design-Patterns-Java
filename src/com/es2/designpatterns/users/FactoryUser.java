package com.es2.designpatterns.users;

import com.es2.designpatterns.exceptions.UserTypeNotFoundException;

public abstract class FactoryUser {

    /**
     *
     * @param type Type of User to be created
     * @return User object created
     * @throws UserTypeNotFoundException Invalid type
     */
    public static User makeUser(String type) throws UserTypeNotFoundException {

        switch (type) {
            case "Gestor":
                return new Gestor();
            case "Motorista":
                return new Motorista();
            default:
                throw new UserTypeNotFoundException();
        }
    }
}

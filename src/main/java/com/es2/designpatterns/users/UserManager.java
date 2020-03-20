package com.es2.designpatterns.users;

import com.es2.designpatterns.exceptions.UserExistingException;
import com.es2.designpatterns.exceptions.UserNotFoundException;
import com.es2.designpatterns.exceptions.UserTypeNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static volatile UserManager instanceLogin;
    private HashMap<String, User> mUserList;


    /**
     * Class Login - Thread safe Singleton responsible for user login
     */
    private UserManager() {
        mUserList = new HashMap<>();
    }


    /**
     * @return Login Instance
     */
    public static UserManager getInstanceLogin() {

        if (instanceLogin == null) {
            synchronized (UserManager.class) {
                if (instanceLogin == null) {
                    instanceLogin = new UserManager();
                }
            }
        }
        return instanceLogin;
    }


    /**
     * @param username Self-explanatory
     * @param password Self-explanatory
     * @return True if login succeed else False
     * @throws UserNotFoundException if User not Found
     */
    public boolean loginUser(String username, String password) throws UserNotFoundException {

        if (mUserList.get(username) == null)
            throw new UserNotFoundException();

        return mUserList.get(username).loginUser(password);
    }


    /**
     * @param type     Type of User to be registered
     * @param username Self-explanatory
     * @param password Self-explanatory
     * @return Different message depending on given params
     */
    public String registerUser(String type, String username, String password) throws UserTypeNotFoundException, UserExistingException {

        if (mUserList.get(username) != null)
            throw new UserExistingException();

        User newUser = FactoryUser.makeUser(type);
        newUser.registerUser(username, password);
        mUserList.put(username, newUser);

        return "Registo com sucesso";
    }


    /**
     * @return available motorista username
     * @throws UserNotFoundException if no available motoristas
     */
    public User getAvailableMotorista() throws UserNotFoundException {

        for (Map.Entry<String, User> userEntry : mUserList.entrySet()) {
            if (userEntry.getValue().getClass().equals(Motorista.class) && (((Motorista) userEntry.getValue()).getStatus())) {
                ((Motorista) userEntry.getValue()).toggleStatus();
                return userEntry.getValue();
            }
        }

        throw new UserNotFoundException();
    }

    /**
     * @param username Motorista Username to free
     * @throws UserNotFoundException if username not found or status available
     */
    public void freeMotorista(String username) throws UserNotFoundException {

        for (Map.Entry<String, User> userEntry : mUserList.entrySet()) {
            if (userEntry.getValue().getClass().equals(Motorista.class) && !(((Motorista) userEntry.getValue()).getStatus()))
                ((Motorista) userEntry.getValue()).toggleStatus();
            return;
        }

        throw new UserNotFoundException();
    }


}

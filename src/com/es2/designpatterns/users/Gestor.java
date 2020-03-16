package com.es2.designpatterns.users;

public class Gestor implements User {

    private String mUsername;
    private String mPassword;

    /**
     *
     * @param password Login received password
     * @return True if password match else false
     */
    @Override
    public boolean loginUser(String password) {
        return mPassword.equals(password);
    }

    /**
     *
     * @param username Set new user username
     * @param password Set new user password
     */
    @Override
    public void registerUser(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    /**
     *
     * @return Username
     */
    @Override
    public String getUsername() {
        return mUsername;
    }

    /**
     *
     * @param newPassword set new password
     */
    @Override
    public void resetPassword(String newPassword) {
        mPassword = newPassword;
    }
}

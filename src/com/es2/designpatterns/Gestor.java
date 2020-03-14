package com.es2.designpatterns;

public class Gestor implements User {

    private String mUsername;
    private String mPassword;

    protected Gestor() {
    }

    @Override
    public boolean loginUser(String password) {
        return mPassword.equals(password);
    }

    @Override
    public void registerUser(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    @Override
    public String getUsername() {
        return mUsername;
    }

    @Override
    public void resetPassword(String newPassword) {
        mPassword = newPassword;
    }
}

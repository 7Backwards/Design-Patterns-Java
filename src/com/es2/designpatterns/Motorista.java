package com.es2.designpatterns;

public class Motorista implements User {

    private String mUsername;
    private String mPassword;
    private String mCarLicense;
    private boolean mStatusAvailable = true;

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

    public void setCarLicense(String carLicenseNr) {
        mCarLicense = carLicenseNr;
    }

    public String getCarLicense() {
        return mCarLicense;
    }

    public void toggleStatus() {
        mStatusAvailable = !mStatusAvailable;
    }

    public boolean getStatus() {
        return mStatusAvailable;
    }
}

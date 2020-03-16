package com.es2.designpatterns.users;

public class Motorista implements User {

    private String mUsername;
    private String mPassword;
    private String mCarLicense;
    private boolean mStatusAvailable = true;

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

    /**
     *
     * @param carLicenseNr Set driver license
     */
    public void setCarLicense(String carLicenseNr) {
        mCarLicense = carLicenseNr;
    }

    /**
     *
     * @return Get driver license
     */
    public String getCarLicense() {
        return mCarLicense;
    }

    /**
     * Toggle driver status - Available / not Available
     */
    public void toggleStatus() {
        mStatusAvailable = !mStatusAvailable;
    }

    /**
     *
     * @return Driver status - True if available - False if not available
     */
    public boolean getStatus() {
        return mStatusAvailable;
    }
}

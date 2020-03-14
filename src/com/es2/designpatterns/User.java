package com.es2.designpatterns;

public interface User {

    boolean loginUser(String password);

    void registerUser(String username, String password);

    String getUsername();

    void resetPassword(String newPassword);
}

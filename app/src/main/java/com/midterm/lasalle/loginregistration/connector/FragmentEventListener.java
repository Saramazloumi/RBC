package com.midterm.lasalle.loginregistration.connector;

import com.midterm.lasalle.loginregistration.model.User;

public interface FragmentEventListener {

    void onRegisterUser(User user);
    void onLoginUser(String email, String password);
    void onUpdateUser(User user);
}

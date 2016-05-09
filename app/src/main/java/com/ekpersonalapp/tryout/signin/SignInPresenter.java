package com.ekpersonalapp.tryout.signin;

/**
 * Created by ekta on 17/2/16.
 */
public interface SignInPresenter {
    void validateEmailAndPassword(String email, String password);

    void showHidePassword(boolean isPasswordVisible);
}

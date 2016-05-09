package com.ekpersonalapp.tryout.signup;

import com.ekpersonalapp.tryout.BaseViewInterface;

/**
 * Created by ekta on 23/2/16.
 */
public interface SignUpView extends BaseViewInterface {

    void showEmailErrorMessage(String errorMessage);

    void showPasswordErrorMessage(String errorMessage);

    void showPassword();

    void hidePassword();

    void navigateToSignInScreen();

}

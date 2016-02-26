package com.ekpersonalapp.tryout.signin;

import com.ekpersonalapp.tryout.BaseViewInterface;

/**
 * Created by ekta on 17/2/16.
 */
public interface SignInView extends BaseViewInterface {
    void showEmailErrorMessage(String errorMessage);

    void showPasswordErrorMessage(String errorMessage);
}

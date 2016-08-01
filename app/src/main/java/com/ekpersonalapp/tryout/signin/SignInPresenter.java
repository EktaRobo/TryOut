package com.ekpersonalapp.tryout.signin;

import android.content.Context;

/**
 * Created by ekta on 17/2/16.
 */
public interface SignInPresenter {
    void validateEmailAndPassword(String email, String password, Context context);

    void showHidePassword(boolean isPasswordVisible);
}

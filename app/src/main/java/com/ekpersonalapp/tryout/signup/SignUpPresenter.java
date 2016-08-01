package com.ekpersonalapp.tryout.signup;

import android.content.Context;

/**
 * Created by ekta on 23/2/16.
 */
public interface SignUpPresenter {
    void validateEmailAndPassword(String userName, String accountId, String password, Context
            context);

    void showHidePassword(boolean isPasswordVisible);
}

package com.ekpersonalapp.tryout.signup;

/**
 * Created by ekta on 23/2/16.
 */
public interface SignUpPresenter {
    void validateEmailAndPassword(String userName, String accountId, String password);

    void showHidePassword(boolean isPasswordVisible);
}

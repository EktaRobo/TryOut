package com.ekpersonalapp.tryout.signup;

/**
 * Created by ekta on 23/2/16.
 */
public interface SignUpInteractor {
    boolean isRegisteredUser(String email);

    void addUserToDatabase(String userName, String accountId, String password);
}

package com.ekpersonalapp.tryout.signup;

import android.content.Context;

/**
 * Created by ekta on 23/2/16.
 */
public interface SignUpInteractor {
    boolean isRegisteredUser(String email, Context context);

    void addUserToDatabase(String userName, String accountId, String password, Context context);
}

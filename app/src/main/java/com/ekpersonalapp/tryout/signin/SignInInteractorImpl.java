package com.ekpersonalapp.tryout.signin;

import android.content.Context;

import com.ekpersonalapp.tryout.database.RealmDatabaseHelper;

/**
 * Created by ekta on 17/2/16.
 */
public class SignInInteractorImpl implements SignInInteractor {

    @Override
    public boolean isRegisteredUser(String email, Context context) {
        return RealmDatabaseHelper.getInstance(context).isAccountInDatabase(email);
    }
}

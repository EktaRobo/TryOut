package com.ekpersonalapp.tryout.signup;

import android.content.Context;

import com.ekpersonalapp.tryout.database.RealmDatabaseHelper;
import com.ekpersonalapp.tryout.database.datamodels.UserData;

/**
 * Created by ekta on 24/2/16.
 */
public class SignUpInteractorImpl implements SignUpInteractor {

    @Override
    public boolean isRegisteredUser(String email, Context context) {
        return RealmDatabaseHelper.getInstance(context).isAccountInDatabase(email);
    }

    @Override
    public void addUserToDatabase(String userName, String accountId, String password, Context
            context) {
        UserData userData = new UserData();
        userData.setUserName(userName);
        userData.setEmail(accountId);
        userData.setPassword(password);
        RealmDatabaseHelper.getInstance(context).addTableContents(userData);
    }
}

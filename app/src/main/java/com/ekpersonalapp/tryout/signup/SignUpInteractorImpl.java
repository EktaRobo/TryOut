package com.ekpersonalapp.tryout.signup;

import android.content.Context;

import com.ekpersonalapp.tryout.database.DatabaseHelper;
import com.ekpersonalapp.tryout.utilities.Constants;

/**
 * Created by ekta on 24/2/16.
 */
public class SignUpInteractorImpl implements SignUpInteractor {
    Context mContext;
    DatabaseHelper mDatabaseHelper;

    public SignUpInteractorImpl(Context context) {
        mContext = context;
        mDatabaseHelper = new DatabaseHelper(context, Constants.AccountsDatabaseEntry
                .DATABASE_NAME);
    }

    @Override
    public boolean isRegisteredUser(String email) {
        return mDatabaseHelper.isAccountInDatabase(email);
    }

    @Override
    public void addUserToDatabase(String userName, String accountId, String password) {
        mDatabaseHelper.addAccountToDatabase(userName, accountId, password);
    }
}

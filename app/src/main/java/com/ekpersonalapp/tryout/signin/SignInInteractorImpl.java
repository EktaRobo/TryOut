package com.ekpersonalapp.tryout.signin;

import android.content.Context;

import com.ekpersonalapp.tryout.database.DatabaseHelper;
import com.ekpersonalapp.tryout.utilities.Constants;

/**
 * Created by ekta on 17/2/16.
 */
public class SignInInteractorImpl implements SignInInteractor {
    Context mContext;
    DatabaseHelper mDatabaseHelper;

    public SignInInteractorImpl(Context context) {
        mContext = context;
        mDatabaseHelper = new DatabaseHelper(context, Constants.AccountsDatabaseEntry
                .DATABASE_NAME);
    }

    @Override
    public boolean isRegisteredUser(String email) {
        return mDatabaseHelper.isAccountInDatabase(email);
    }
}

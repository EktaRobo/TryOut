package com.ekpersonalapp.tryout.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ekpersonalapp.tryout.utilities.Constants;

/**
 * Created by ekta on 17/2/16.
 */
public class UserAccountsDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user_accounts.db";

    public UserAccountsDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_USER_ACCOUNT_DATABASE_CREATE_TABLE = "CREATE TABLE " + Constants
                .AccountsDatabaseEntry.TABLE_NAME + " ( " + Constants.AccountsDatabaseEntry.EMAIL
                + "TEXT UNIQUE NOT NULL, " + Constants.AccountsDatabaseEntry.PASSWORD + "TEXT " +
                "UNIQUE NOT NULL ); ";
        sqLiteDatabase.execSQL(SQL_USER_ACCOUNT_DATABASE_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Constants.AccountsDatabaseEntry
                .TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
}

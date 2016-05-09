package com.ekpersonalapp.tryout.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.ekpersonalapp.tryout.utilities.Constants;

/**
 * Created by ekta on 17/2/16.
 */
public class UserAccountsDatabase {
    private static UserAccountsDatabase sUserAccountsDatabase;
    private boolean isAccountInDatabase;

    private UserAccountsDatabase() {

    }

    public static UserAccountsDatabase getInstance() {
        if (sUserAccountsDatabase == null) {
            sUserAccountsDatabase = new UserAccountsDatabase();
        }
        return sUserAccountsDatabase;
    }

    public void createTable(SQLiteDatabase sqLiteDatabase) {
        final String SQL_USER_ACCOUNT_DATABASE_CREATE_TABLE = "CREATE TABLE " + Constants
                .AccountsDatabaseEntry.TABLE_NAME + " ( " + Constants.AccountsDatabaseEntry
                .USER_NAME + " TEXT UNIQUE NOT NULL, " + Constants.AccountsDatabaseEntry.EMAIL
                + " TEXT UNIQUE NOT NULL, " + Constants.AccountsDatabaseEntry.PASSWORD + " TEXT " +
                "UNIQUE NOT NULL ); ";
        sqLiteDatabase.execSQL(SQL_USER_ACCOUNT_DATABASE_CREATE_TABLE);

    }

    public void addTableContent(SQLiteDatabase sqLiteDatabase, String userName
            , String userAccount, String password) {
        ContentValues values = new ContentValues();
        values.put(Constants.AccountsDatabaseEntry.USER_NAME, userName);
        values.put(Constants.AccountsDatabaseEntry.EMAIL, userAccount);
        values.put(Constants.AccountsDatabaseEntry.PASSWORD, password);
        try {
            sqLiteDatabase.insertOrThrow(Constants.AccountsDatabaseEntry.TABLE_NAME, null, values);
        } catch (SQLiteConstraintException e) {
            isAccountInDatabase = true;
        }
    }

    public boolean isAccountInDatabase(SQLiteDatabase sqLiteDatabase, String email) {
        String[] tableColumns = new String[]{Constants.AccountsDatabaseEntry.USER_NAME,
                Constants.AccountsDatabaseEntry.EMAIL, Constants.AccountsDatabaseEntry.PASSWORD};
        String whereClause = Constants.AccountsDatabaseEntry.EMAIL + " = ?";
        String[] whereArgs = new String[]{email};
        try {
            Cursor cursor = sqLiteDatabase.query(Constants.AccountsDatabaseEntry.TABLE_NAME,
                    tableColumns, whereClause, whereArgs, null, null, null);
            while (cursor.moveToNext()) {
                isAccountInDatabase = true;
            }
            sqLiteDatabase.close();
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            isAccountInDatabase = false;
        }
        return isAccountInDatabase;
    }
}

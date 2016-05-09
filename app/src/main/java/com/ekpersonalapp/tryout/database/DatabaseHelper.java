package com.ekpersonalapp.tryout.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ekpersonalapp.tryout.utilities.Constants;

/**
 * Created by ekta on 22/2/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 4;
    private String mDatabaseName;

    public DatabaseHelper(Context context, String name) {
        super(context, name, null, DATABASE_VERSION);
        mDatabaseName = name;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        switch (mDatabaseName) {
            case Constants.AccountsDatabaseEntry.DATABASE_NAME:
                UserAccountsDatabase.getInstance().createTable(sqLiteDatabase);
                break;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        switch (mDatabaseName) {
            case Constants.AccountsDatabaseEntry.DATABASE_NAME:
                upgradeTable(sqLiteDatabase, Constants.AccountsDatabaseEntry.TABLE_NAME);
                break;
        }
    }

    private void upgradeTable(SQLiteDatabase sqLiteDatabase, String tableName) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }

    public void addAccountToDatabase(String userName, String userAccount, String password) {
        UserAccountsDatabase.getInstance().addTableContent(this.getWritableDatabase(), userName,
                userAccount, password);
    }

    public boolean isAccountInDatabase(String email) {
        return UserAccountsDatabase.getInstance().isAccountInDatabase(this.getReadableDatabase(),
                email);
    }
}

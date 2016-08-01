package com.ekpersonalapp.tryout;

import android.app.Application;

import com.ekpersonalapp.tryout.database.RealmDatabaseHelper;

public class TryOutApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmDatabaseHelper.getInstance(this);
    }
}

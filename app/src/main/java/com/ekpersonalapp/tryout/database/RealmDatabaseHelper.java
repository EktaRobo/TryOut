package com.ekpersonalapp.tryout.database;

import android.content.Context;

import com.ekpersonalapp.tryout.database.datamodels.UserData;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by ekta on 17/5/16.
 */
public class RealmDatabaseHelper {
    private static RealmDatabaseHelper sRealmDatabaseHelper;
    private Realm mRealm;

    private RealmDatabaseHelper(Context context) {
        // Create a RealmConfiguration which is to locate Realm file in package's "files" directory.
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(context)
                .deleteRealmIfMigrationNeeded().build();
        // Get a Realm instance for this thread
        Realm.setDefaultConfiguration(realmConfig);
        mRealm = Realm.getInstance(realmConfig);

    }

    public static RealmDatabaseHelper getInstance(Context context) {
        if (sRealmDatabaseHelper == null) {
            sRealmDatabaseHelper = new RealmDatabaseHelper(context);
        }
        return sRealmDatabaseHelper;
    }

    public void addTableContents(UserData userData) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(userData);
        mRealm.commitTransaction();

    }

    public boolean isAccountInDatabase(String email) {
        try {
            RealmResults<UserData> realmResults = mRealm.where(UserData.class).equalTo("mEmail",
                    email).findAll();
            if (realmResults != null) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            return false;
        }
//        return false;

    }

    public void deleteDatabase() {
        mRealm.beginTransaction();
        mRealm.deleteAll();
        mRealm.commitTransaction();
    }
}

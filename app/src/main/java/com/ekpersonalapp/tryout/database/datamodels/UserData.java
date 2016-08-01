package com.ekpersonalapp.tryout.database.datamodels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by ekta on 9/5/16.
 */
public class UserData extends RealmObject {
    @PrimaryKey
    @Required
    private String mEmail;
    private String mUserName;
    private String mPassword;

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}

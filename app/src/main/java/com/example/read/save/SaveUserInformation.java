package com.example.read.save;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveUserInformation {
    private static final String prefsUserName = "prefsUserName";
    private static final String prefsUserPassword = "prefsUserPassword";
    private static final String keyUserName = "keyUserName";
    private static final String keyUserPassword = "keyUserPassword";

    private final SharedPreferences userNameSharePreference;
    private final SharedPreferences userPasswordSharePreference;


    public SaveUserInformation(Context context) {
        userNameSharePreference = context.getSharedPreferences(prefsUserName,Context.MODE_PRIVATE);
        userPasswordSharePreference = context.getSharedPreferences(prefsUserPassword,Context.MODE_PRIVATE);
    }

    public String getUserName () {
        return userNameSharePreference.getString(keyUserName,null);
    }

    public String getUserPassword () {
        return userPasswordSharePreference.getString(keyUserPassword,null);
    }

    public void setUserName (String userName) {
        SharedPreferences.Editor editor = userNameSharePreference.edit();
        editor.putString(keyUserName,userName);
        editor.apply();
    }

    public void setPassword (String userPassword) {
        SharedPreferences.Editor editor = userPasswordSharePreference.edit();
        editor.putString(keyUserPassword,userPassword);
        editor.apply();
    }

}

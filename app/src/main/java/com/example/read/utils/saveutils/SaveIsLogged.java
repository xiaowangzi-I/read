package com.example.read.utils.saveutils;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveIsLogged {
    private static final String prefsIsLogged = "prefsIsLogged";
    private static final String keyIsLogged = "keyIsLogged";

    private final SharedPreferences isLoggedSharePreferences;

    public SaveIsLogged(Context context) {
        isLoggedSharePreferences = context.getSharedPreferences(prefsIsLogged, Context.MODE_PRIVATE);
    }

    public boolean getIsLogged() {
        return isLoggedSharePreferences.getBoolean(keyIsLogged, false);
    }

    public void setIsLogged (boolean isLogged) {
        SharedPreferences.Editor editor = isLoggedSharePreferences.edit();
        editor.putBoolean(keyIsLogged, isLogged);
        editor.apply();
    }
}

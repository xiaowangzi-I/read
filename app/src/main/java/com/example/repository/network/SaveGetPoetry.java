package com.example.read.save;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveGetPoetry {

    private static final String prefsPoetry = "poetry";
    private static final String keyPoetry = "keyPoetry";

    private final SharedPreferences poetrySharedPreferences;

    public SaveGetPoetry (Context context) {
        poetrySharedPreferences = context.getSharedPreferences(prefsPoetry, Context.MODE_PRIVATE);
    }

    public void setPoetry (String responseData) {
        SharedPreferences.Editor editor = poetrySharedPreferences.edit();
        editor.putString(keyPoetry, responseData);
        editor.apply();
    }

    public String getPoetry () {
        return poetrySharedPreferences.getString(keyPoetry, null);
    }
}

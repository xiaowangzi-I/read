package com.example.read.utils.saveutils;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveTime {

    private static final String prefsTime = "time";
    private static final String keyTime = "keyTime";
    private final SharedPreferences timeSharePreference;

    public SaveTime(Context context) {
        timeSharePreference = context.getSharedPreferences(prefsTime, Context.MODE_PRIVATE);
    }

    public void setTime(Long time) {
        SharedPreferences.Editor editor = timeSharePreference.edit();
        editor.putLong(keyTime, time);
        editor.apply();
    }

    public Long getTime() {
        return timeSharePreference.getLong(keyTime, 0);
    }
}

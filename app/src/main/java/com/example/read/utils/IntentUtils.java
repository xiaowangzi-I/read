package com.example.read.utils;

import android.content.Context;
import android.content.Intent;

public class IntentUtils {

    private static Intent intent;

    public static void intent (Context thisContext, Class<?> targetActivityClass) {
        intent = new Intent(thisContext, targetActivityClass);
        thisContext.startActivity(intent);
    }

    public static void intentPutExtraString(Context thisContext, Class<?> targetActivityClass, String value, String keyIntent) {
        intent = new Intent(thisContext, targetActivityClass);
        intent.putExtra(keyIntent, value);
        thisContext.startActivity(intent);
    }

    public static String intentGetExtraString(String keyIntent) {
        return intent.getStringExtra(keyIntent);
    }
}

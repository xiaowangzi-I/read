package com.example.read.utils;

import android.content.Context;
import android.content.Intent;

public class IntentUtils {

    public static void intent (Context thisContext, Class<?> targetActivityClass) {
        Intent intent = new Intent(thisContext, targetActivityClass);
        thisContext.startActivity(intent);
    }
}

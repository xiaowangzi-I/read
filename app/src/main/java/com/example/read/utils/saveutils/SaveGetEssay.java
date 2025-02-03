package com.example.read.utils.saveutils;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveGetEssay {

    private static final String prefsPoetry = "poetry";
    private static final String prefsNews = "news";
    private static final String keyPoetry = "keyPoetry";
    private static final String keyNews = "keyNews";

    private final SharedPreferences poetrySharedPreferences;
    private final SharedPreferences newsSharedPreferences;

    public SaveGetEssay(Context context) {
        poetrySharedPreferences = context.getSharedPreferences(prefsPoetry, Context.MODE_PRIVATE);
        newsSharedPreferences = context.getSharedPreferences(prefsNews, Context.MODE_PRIVATE);
    }

    public void setPoetry(String responseData) {
        SharedPreferences.Editor editor = poetrySharedPreferences.edit();
        editor.putString(keyPoetry, responseData);
        editor.apply();
    }

    public String getPoetry() {
        return poetrySharedPreferences.getString(keyPoetry, null);
    }

    public void setNews(String responseData) {
        SharedPreferences.Editor editor = newsSharedPreferences.edit();
        editor.putString(keyNews, responseData);
        editor.apply();
    }

    public String getNews() {
        return newsSharedPreferences.getString(keyNews, null);
    }
}

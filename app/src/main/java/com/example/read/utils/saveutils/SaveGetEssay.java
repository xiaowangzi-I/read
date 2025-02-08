package com.example.read.utils.saveutils;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveGetEssay {

    private static final String prefsPoetry = "poetry";
    private static final String prefsNews = "news";
    private static final String prefsProse = "prose";
    private static final String prefsDailyNews = "dailyNews";


    private static final String keyPoetry = "keyPoetry";
    private static final String keyNews = "keyNews";
    private static final String keyProse = "keyProse";
    private static final String keyDailyNews = "keyDailyNews";

    private final SharedPreferences poetrySharedPreferences;
    private final SharedPreferences newsSharedPreferences;
    private final SharedPreferences proseSharedPreferences;
    private final SharedPreferences dailyNewsSharedPreferences;

    public SaveGetEssay(Context context) {
        poetrySharedPreferences = context.getSharedPreferences(prefsPoetry, Context.MODE_PRIVATE);
        newsSharedPreferences = context.getSharedPreferences(prefsNews, Context.MODE_PRIVATE);
        proseSharedPreferences = context.getSharedPreferences(prefsProse, Context.MODE_PRIVATE);
        dailyNewsSharedPreferences = context.getSharedPreferences(prefsDailyNews, Context.MODE_PRIVATE);
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

    public void setProse(String responseData) {
        SharedPreferences.Editor editor = proseSharedPreferences.edit();
        editor.putString(keyProse, responseData);
        editor.apply();
    }

    public String getProse() {
        return proseSharedPreferences.getString(keyProse, null);
    }

    public void setDailyNews(String responseData) {
        SharedPreferences.Editor editor = dailyNewsSharedPreferences.edit();
        editor.putString(keyDailyNews, responseData);
        editor.apply();
    }

    public String getDailyNews() {
        return dailyNewsSharedPreferences.getString(keyDailyNews, null);
    }
}

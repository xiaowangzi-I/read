package com.example.read.repository.model;

import android.content.Context;

import com.example.read.utils.saveutils.SaveGetEssay;

public enum Essay {
    POETRY {
        @Override
        public void set(Context context, String responseData) {
            new SaveGetEssay(context).setPoetry(responseData);
        }

        @Override
        public String get(Context context) {
            return new SaveGetEssay(context).getPoetry();
        }
    },
    NEWS {
        @Override
        public void set(Context context, String responseData) {
            new SaveGetEssay(context).setNews(responseData);
        }

        @Override
        public String get(Context context) {
            return new SaveGetEssay(context).getNews();
        }
    };

    public abstract void set(Context context, String responseData);
    public abstract String get(Context context);
}
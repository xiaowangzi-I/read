package com.example.read.repository.model;

import android.content.Context;
import android.util.Log;

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
    },

    PROSE {
        @Override
        public void set(Context context, String responseData) {
            new SaveGetEssay(context).setProse(responseData);
        }

        @Override
        public String get(Context context) {
            return new SaveGetEssay(context).getProse();
        }
    },

    DAILYNEWS {
        @Override
        public void set(Context context, String responseData) {
            new SaveGetEssay(context).setDailyNews(responseData);
        }

        @Override
        public String get(Context context) {
            return new SaveGetEssay(context).getDailyNews();
        }
    };

    public abstract void set(Context context, String responseData);
    public abstract String get(Context context);
}
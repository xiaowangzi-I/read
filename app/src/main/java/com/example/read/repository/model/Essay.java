/**
* description ：每个枚举实例代表一种文章类型，并提供设置和获取数据的方法。 TODO:用于管理不同类型的文章数据的存储和获取。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月3日，12:36
*/
package com.example.read.repository.model;

import android.content.Context;
import android.util.Log;

import com.example.read.utils.saveutils.SaveGetEssay;

public enum Essay {
    /**
     * 诗歌类型的文章。
     */
    POETRY {
        @Override
        public void set(Context context, String responseData) {
            // 调用 SaveGetEssay 的 setPoetry 方法保存诗歌数据
            new SaveGetEssay(context).setPoetry(responseData);
        }

        @Override
        public String get(Context context) {
            // 调用 SaveGetEssay 的 getPoetry 方法获取保存的诗歌数据
            return new SaveGetEssay(context).getPoetry();
        }
    },
    /**
     * 新闻类型的文章。
     */
    NEWS {
        @Override
        public void set(Context context, String responseData) {
            // 调用 SaveGetEssay 的 setNews 方法保存新闻数据
            new SaveGetEssay(context).setNews(responseData);
        }

        @Override
        public String get(Context context) {
            // 调用 SaveGetEssay 的 getNews 方法获取保存的新闻数据
            return new SaveGetEssay(context).getNews();
        }
    },
    /**
     * 散文类型的文章。
     */
    PROSE {
        @Override
        public void set(Context context, String responseData) {
            // 调用 SaveGetEssay 的 setProse 方法保存散文数据
            new SaveGetEssay(context).setProse(responseData);
        }

        @Override
        public String get(Context context) {
            // 调用 SaveGetEssay 的 getProse 方法获取保存的散文数据
            return new SaveGetEssay(context).getProse();
        }
    },
    /**
     * 每日新闻类型的文章。
     */
    DAILYNEWS {
        @Override
        public void set(Context context, String responseData) {
            // 调用 SaveGetEssay 的 setDailyNews 方法保存知乎日报数据
            new SaveGetEssay(context).setDailyNews(responseData);
        }

        @Override
        public String get(Context context) {
            // 调用 SaveGetEssay 的 getDailyNews 方法获取保存的知乎日报数据
            return new SaveGetEssay(context).getDailyNews();
        }
    };

    /**
     * 设置文章数据的方法。
     * @param context 应用程序上下文。
     * @param responseData 要保存的文章数据。
     */
    public abstract void set(Context context, String responseData);

    /**
     * 获取文章数据的方法。
     * @param context 应用程序上下文。
     * @return 返回保存的文章数据。
     */
    public abstract String get(Context context);
}
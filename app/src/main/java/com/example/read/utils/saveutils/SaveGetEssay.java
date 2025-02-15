/**
* description ：使用 SharedPreferences 来存储和检索诗歌、新闻、散文和每日新闻的数据。 TODO:SaveGetEssay 类用于保存和获取不同类型文章的数据。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月29日，20:59
*/
package com.example.read.utils.saveutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 *
 */
public class SaveGetEssay {

    // SharedPreferences 文件名常量
    private static final String prefsPoetry = "poetry";          // 诗歌数据存储文件名
    private static final String prefsNews = "news";              // 新闻数据存储文件名
    private static final String prefsProse = "prose";            // 散文数据存储文件名
    private static final String prefsDailyNews = "dailyNews";    // 每日新闻数据存储文件名

    // SharedPreferences 键名常量
    private static final String keyPoetry = "keyPoetry";         // 诗歌数据的键名
    private static final String keyNews = "keyNews";             // 新闻数据的键名
    private static final String keyProse = "keyProse";           // 散文数据的键名
    private static final String keyDailyNews = "keyDailyNews";   // 每日新闻数据的键名

    // SharedPreferences 实例
    private final SharedPreferences poetrySharedPreferences;       // 用于存储诗歌数据
    private final SharedPreferences newsSharedPreferences;         // 用于存储新闻数据
    private final SharedPreferences proseSharedPreferences;        // 用于存储散文数据
    private final SharedPreferences dailyNewsSharedPreferences;    // 用于存储每日新闻数据

    /**
     * 构造函数，初始化 SharedPreferences 实例。
     *
     * @param context 应用程序上下文。
     */
    public SaveGetEssay(Context context) {
        poetrySharedPreferences = context.getSharedPreferences(prefsPoetry, Context.MODE_PRIVATE);
        newsSharedPreferences = context.getSharedPreferences(prefsNews, Context.MODE_PRIVATE);
        proseSharedPreferences = context.getSharedPreferences(prefsProse, Context.MODE_PRIVATE);
        dailyNewsSharedPreferences = context.getSharedPreferences(prefsDailyNews, Context.MODE_PRIVATE);
    }

    /**
     * 保存诗歌数据到 SharedPreferences。
     *
     * @param responseData 要保存的诗歌数据。
     */
    public void setPoetry(String responseData) {
        SharedPreferences.Editor editor = poetrySharedPreferences.edit();
        editor.putString(keyPoetry, responseData);
        editor.apply();
    }

    /**
     * 从 SharedPreferences 获取保存的诗歌数据。
     *
     * @return 保存的诗歌数据，如果未找到则返回 null。
     */
    public String getPoetry() {
        return poetrySharedPreferences.getString(keyPoetry, null);
    }

    /**
     * 保存新闻数据到 SharedPreferences。
     *
     * @param responseData 要保存的新闻数据。
     */
    public void setNews(String responseData) {
        SharedPreferences.Editor editor = newsSharedPreferences.edit();
        editor.putString(keyNews, responseData);
        editor.apply();
    }

    /**
     * 从 SharedPreferences 获取保存的新闻数据。
     *
     * @return 保存的新闻数据，如果未找到则返回 null。
     */
    public String getNews() {
        return newsSharedPreferences.getString(keyNews, null);
    }

    /**
     * 保存散文数据到 SharedPreferences。
     *
     * @param responseData 要保存的散文数据。
     */
    public void setProse(String responseData) {
        SharedPreferences.Editor editor = proseSharedPreferences.edit();
        editor.putString(keyProse, responseData);
        editor.apply();
    }

    /**
     * 从 SharedPreferences 获取保存的散文数据。
     *
     * @return 保存的散文数据，如果未找到则返回 null。
     */
    public String getProse() {
        return proseSharedPreferences.getString(keyProse, null);
    }

    /**
     * 保存每日新闻数据到 SharedPreferences。
     *
     * @param responseData 要保存的每日新闻数据。
     */
    public void setDailyNews(String responseData) {
        SharedPreferences.Editor editor = dailyNewsSharedPreferences.edit();
        editor.putString(keyDailyNews, responseData);
        editor.apply();
    }

    /**
     * 从 SharedPreferences 获取保存的每日新闻数据。
     *
     * @return 保存的每日新闻数据，如果未找到则返回 null。
     */
    public String getDailyNews() {
        return dailyNewsSharedPreferences.getString(keyDailyNews, null);
    }
}
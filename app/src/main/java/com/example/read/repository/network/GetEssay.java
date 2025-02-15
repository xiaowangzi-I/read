/**
* description ：通过api获取文章 TODO:获得一篇文章
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月29日，16:30
*/
package com.example.read.repository.network;

import android.content.Context;

import com.example.read.repository.model.Essay;
import com.example.read.utils.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class GetEssay {
    /**
     * 获取一首随机诗歌并保存到本地。
     * 使用 Whyta API 获取随机诗歌数据，并通过 Essay.POETRY 枚举实例保存数据。
     *
     * @param context 应用程序上下文，用于保存数据。
     * @throws MalformedURLException 如果提供的 URL 格式不正确。
     */
    public static void getAPoetry(Context context) throws MalformedURLException {
        URL url = new URL("https://whyta.cn/api/tx/poetries?key=96f163cda80b&num=1&page=1&rand=1");
        NetworkUtils.networkRequestAndSavesGet(url, context, Essay.POETRY);
    }

    /**
     * 获取新闻列表并保存到本地。
     * 使用 Whyta API 获取新闻列表数据，并通过 Essay.NEWS 枚举实例保存数据。
     *
     * @param context 应用程序上下文，用于保存数据。
     * @throws MalformedURLException 如果提供的 URL 格式不正确。
     */
    public static void getNews(Context context) throws MalformedURLException {
        URL url = new URL("https://whyta.cn/api/tx/bulletin?key=96f163cda80b");
        NetworkUtils.networkRequestAndSavesGet(url, context, Essay.NEWS);
    }

    /**
     * 获取一篇散文并保存到本地。
     * 使用 Alapi API 获取散文数据，并通过 Essay.PROSE 枚举实例保存数据。
     *
     * @param context 应用程序上下文，用于保存数据。
     * @throws MalformedURLException 如果提供的 URL 格式不正确。
     */
    public static void getAProse(Context context) throws MalformedURLException {
        URL url = new URL("https://v2.alapi.cn/api/one?token=LwExDtUWhF3rH5ib");
        NetworkUtils.networkRequestAndSavesGet(url, context, Essay.PROSE);
    }

    /**
     * 获取每日新闻并保存到本地。
     * 使用 Alapi API 获取每日新闻数据，并通过 Essay.DAILYNEWS 枚举实例保存数据。
     *
     * @param context 应用程序上下文，用于保存数据。
     * @throws MalformedURLException 如果提供的 URL 格式不正确。
     */
    public static void getDailyNews(Context context) throws MalformedURLException {
        URL url = new URL("https://v3.alapi.cn/api/zhihu?token=ucjjjrduhgtia30hr6xfvmtb85rsli");
        NetworkUtils.networkRequestAndSavesGet(url, context, Essay.DAILYNEWS);
    }
}
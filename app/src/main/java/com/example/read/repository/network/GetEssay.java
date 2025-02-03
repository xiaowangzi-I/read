package com.example.read.repository.network;

import android.content.Context;

import com.example.read.repository.model.Essay;
import com.example.read.utils.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class GetEssay {

    public static void getAPoetry (Context context) throws MalformedURLException {
        URL url = new URL("https://whyta.cn/api/tx/poetries?key=96f163cda80b&num=1&page=1&rand=1");
        NetworkUtils.networkRequestsGet(url, context, Essay.POETRY);
    }

    public static void getNews (Context context) throws MalformedURLException {
        URL url = new URL("https://whyta.cn/api/tx/bulletin?key=96f163cda80b");
        NetworkUtils.networkRequestsGet(url, context, Essay.NEWS);
    }
}

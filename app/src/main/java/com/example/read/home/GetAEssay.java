package com.example.read.home;

import android.content.Context;

import com.example.utils.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class Search {

//    private static final String key = "96f163cda80b";
//    private static final int num = 3;
//    private static final int page = 1;
//    private static final int rand = 1;
//" + key + "&num=" + num + "&page=" + page + "&rand=" + rand + "&title=" + title
//
//
    public void searchPoetry (String title, Context context) throws MalformedURLException {
        URL url = new URL("https://whyta.cn/api/tx/poetries?key=96f163cda80b&num=1&page=1&rand=1");
        NetworkUtils.networkRequestsGet(url, context);
    }
}

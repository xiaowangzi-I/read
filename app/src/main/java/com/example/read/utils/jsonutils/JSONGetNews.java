package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

import java.util.List;

public class JSONGetNews {
    private GetNewsResult result;

    public GetNewsResult getResult() {
        return result;
    }

    public static class GetNewsResult {
        private List<GetNews> list;

        public List<GetNews> getList() {
            return list;
        }

        public static class GetNews {
            private String mtime;
            private String title;
            private String digest;

            public String getTitle() {
                return title;
            }
        }
    }

    public static JSONGetNews parseJSON (String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetNews.class);
    }
}

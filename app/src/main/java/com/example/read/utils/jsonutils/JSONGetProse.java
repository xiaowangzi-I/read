package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

public class JSONGetProse {
    private int code;
    private GetProseData data;

    public GetProseData getData() {
        return data;
    }

    public static class GetProseData {
        private String title;
        private String subtitle;
        private String content;

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return subtitle;
        }

        public String getContent() {
            return content;
        }
    }

    public static JSONGetProse parseJSON (String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetProse.class);
    }
}

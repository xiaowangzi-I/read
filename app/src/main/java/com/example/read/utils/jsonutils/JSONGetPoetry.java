package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

import java.util.List;



public class JSONGetPoetry {
    private int code;
    private String msg;
    private GetPoetryResult result;

    public GetPoetryResult getResult() {
        return result;
    }

    public static class GetPoetryResult {
        private List<GetPoetry> list;

        public List<GetPoetry> getList() {
            return list;
        }


        public static class GetPoetry {
            private String title;
            private String content;
            private String author;

            public String getTitle() {
                return title;
            }

            public String getAuthor() {
                return author;
            }

            public String getContent() {
                return content;
            }
        }
    }

    public static JSONGetPoetry parseJSON(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetPoetry.class);
    }
}

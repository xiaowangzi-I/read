package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

import java.util.List;

public class JSONGetDailyNews {

    private GetDailyNewsData data;

    public GetDailyNewsData getData() {
        return data;
    }

    public static class GetDailyNewsData {
        private String date;
        private List<GetDailyNews> stories;

        public String getDate() {
            return date;
        }

        public List<GetDailyNews> getStories() {
            return stories;
        }


        public static class GetDailyNews {

            private String url;
            private String hint;
            private String title;
            private List<String> images;

            public String getTitle() {
                return title;
            }

            public String getHint() {
                return hint;
            }

            public String getUrl() {
                return url;
            }

            public List<String> getImages() {
                return images;
            }
        }
    }

    public static JSONGetDailyNews parseJSON (String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetDailyNews.class);
    }
}

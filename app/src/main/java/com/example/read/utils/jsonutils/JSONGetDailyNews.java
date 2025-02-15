/**
* description ：提供了嵌套的数据结构和解析方法，用于将 JSON 字符串映射为 Java 对象。 TODO:JSONGetDailyNews 类用于解析知乎日报的 JSON 数据。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月3日，21:12
*/
package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

import java.util.List;

public class JSONGetDailyNews {

    /**
     * 包含知乎日报数据的内部类。
     * 包含知乎日报和新闻列表。
     */
    private GetDailyNewsData data;

    /**
     * 获取知乎日报数据。
     *
     * @return GetDailyNewsData 对象，包含知乎日报和新闻列表。
     */
    public GetDailyNewsData getData() {
        return data;
    }

    /**
     * 知乎日报数据类，包含日期和新闻列表。
     */
    public static class GetDailyNewsData {
        /**
         * 新闻日期。
         */
        private String date;

        /**
         * 新闻列表。
         */
        private List<GetDailyNews> stories;

        /**
         * 获取新闻日期。
         *
         * @return 新闻日期字符串。
         */
        public String getDate() {
            return date;
        }

        /**
         * 获取新闻列表。
         *
         * @return 新闻列表。
         */
        public List<GetDailyNews> getStories() {
            return stories;
        }

        /**
         * 每日新闻类，包含单条新闻的详细信息。
         */
        public static class GetDailyNews {

            /**
             * 新闻链接。
             */
            private String url;

            /**
             * 新闻标题。
             */
            private String title;

            /**
             * 新闻图片列表。
             */
            private List<String> images;

            /**
             * 获取新闻标题。
             *
             * @return 新闻标题。
             */
            public String getTitle() {
                return title;
            }

            /**
             * 获取文章链接。
             *
             * @return 新闻链接。
             */
            public String getUrl() {
                return url;
            }

            /**
             * 获取新闻图片列表。
             *
             * @return 新闻图片列表。
             */
            public List<String> getImages() {
                return images;
            }
        }
    }

    /**
     * 使用 Gson 解析知乎日报的 JSON 数据。
     *
     * @param jsonString JSON 字符串。
     * @return 解析后的 JSONGetDailyNews 对象。
     */
    public static JSONGetDailyNews parseJSON(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetDailyNews.class);
    }
}
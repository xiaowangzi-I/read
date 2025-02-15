/**
* description ：提供嵌套的数据结构，用于将 JSON 字符串映射为 Java 对象。 TODO:JSONGetNews 类用于解析新闻数据的 JSON 格式。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月2日，17:09
*/
package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

import java.util.List;

public class JSONGetNews {
    /**
     * 包含新闻结果的内部类。
     * 包含新闻列表。
     */
    private GetNewsResult result;

    /**
     * 获取新闻结果对象。
     *
     * @return GetNewsResult 对象，包含新闻列表。
     */
    public GetNewsResult getResult() {
        return result;
    }

    /**
     * 新闻结果类，包含新闻列表。
     */
    public static class GetNewsResult {
        /**
         * 新闻列表。
         */
        private List<GetNews> list;

        /**
         * 获取新闻列表。
         *
         * @return 新闻列表。
         */
        public List<GetNews> getList() {
            return list;
        }

        /**
         * 新闻类，包含单条新闻的详细信息。
         */
        public static class GetNews {
            /**
             * 新闻发布时间。
             */
            private String mtime;

            /**
             * 新闻标题。
             */
            private String title;

            /**
             * 新闻摘要。
             */
            private String digest;

            /**
             * 获取新闻标题。
             *
             * @return 新闻标题。
             */
            public String getTitle() {
                return title;
            }

            /**
             * 获取新闻发布时间。
             *
             * @return 新闻发布时间。
             */
            public String getMtime() {
                return mtime;
            }

            /**
             * 获取新闻摘要。
             *
             * @return 新闻摘要。
             */
            public String getDigest() {
                return digest;
            }
        }
    }

    /**
     * 使用 Gson 解析新闻的 JSON 数据。
     *
     * @param jsonString JSON 字符串。
     * @return 解析后的 JSONGetNews 对象。
     */
    public static JSONGetNews parseJSON(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetNews.class);
    }
}
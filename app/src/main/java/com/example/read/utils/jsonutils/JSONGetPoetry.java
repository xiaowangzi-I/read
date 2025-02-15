/**
* description ：提供嵌套的数据结构，用于将 JSON 字符串映射为 Java 对象。 TODO:JSONGetPoetry 类用于解析诗歌数据的 JSON 格式。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年1月31日，16:18
*/
package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

import java.util.List;

public class JSONGetPoetry {
    /**
     * HTTP 状态码。
     */
    private int code;

    /**
     * 包含诗歌结果的内部类。
     */
    private GetPoetryResult result;

    /**
     * 获取诗歌结果对象。
     *
     * @return GetPoetryResult 对象，包含诗歌列表。
     */
    public GetPoetryResult getResult() {
        return result;
    }

    /**
     * 诗歌结果类，包含诗歌列表。
     */
    public static class GetPoetryResult {
        /**
         * 诗歌列表。
         */
        private List<GetPoetry> list;

        /**
         * 获取诗歌列表。
         *
         * @return 诗歌列表。
         */
        public List<GetPoetry> getList() {
            return list;
        }

        /**
         * 诗歌类，包含单条诗歌的详细信息。
         */
        public static class GetPoetry {
            /**
             * 诗歌标题。
             */
            private String title;

            /**
             * 诗歌内容。
             */
            private String content;

            /**
             * 诗歌作者。
             */
            private String author;

            /**
             * 获取诗歌标题。
             *
             * @return 诗歌标题。
             */
            public String getTitle() {
                return title;
            }

            /**
             * 获取诗歌内容。
             *
             * @return 诗歌内容。
             */
            public String getContent() {
                return content;
            }

            /**
             * 获取诗歌作者。
             *
             * @return 诗歌作者。
             */
            public String getAuthor() {
                return author;
            }
        }
    }

    /**
     * 使用 Gson 解析诗歌的 JSON 数据。
     *
     * @param jsonString JSON 字符串。
     * @return 解析后的 JSONGetPoetry 对象。
     */
    public static JSONGetPoetry parseJSON(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetPoetry.class);
    }
}
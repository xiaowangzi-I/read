/**
* description ：提供了数据结构和解析方法，用于将 JSON 字符串映射为 Java 对象。 TODO:JSONGetProse 类用于解析散文数据的 JSON 格式。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月3日，14:46
*/
package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

public class JSONGetProse {
    /**
     * HTTP 状态码，标识请求是否成功。
     */
    private int code;

    /**
     * 包含散文数据的内部类。
     */
    private GetProseData data;

    /**
     * 获取散文数据对象。
     *
     * @return GetProseData 对象，包含散文的标题、副标题和内容。
     */
    public GetProseData getData() {
        return data;
    }

    /**
     * 散文数据类，包含散文的标题、副标题和内容。
     */
    public static class GetProseData {
        /**
         * 散文的主标题。
         */
        private String title;

        /**
         * 散文的副标题（通常为作者或来源）。
         */
        private String subtitle;

        /**
         * 散文的正文内容。
         */
        private String content;

        /**
         * 获取散文的主标题。
         *
         * @return 散文的主标题。
         */
        public String getTitle() {
            return title;
        }

        /**
         * 获取散文的副标题（作者或来源）。
         *
         * @return 散文的副标题。
         */
        public String getAuthor() {
            return subtitle;
        }

        /**
         * 获取散文的正文内容。
         *
         * @return 散文的正文内容。
         */
        public String getContent() {
            return content;
        }
    }

    /**
     * 使用 Gson 解析散文的 JSON 数据。
     *
     * @param jsonString JSON 字符串。
     * @return 解析后的 JSONGetProse 对象。
     */
    public static JSONGetProse parseJSON(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetProse.class);
    }
}
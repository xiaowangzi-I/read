/**
* description ：提供了数据结构和解析方法，用于将 JSON 字符串映射为 Java 对象。 TODO:JSONGetWord 类用于解析汉字字典数据的 JSON 格式。
* author : 王子旻
* email : 2461095759@qq.com
* date : 2025年2月6日，14:47
*/
package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

import java.util.List;

public class JSONGetWord {
    /**
     * 包含汉字字典数据的列表。
     */
    private List<GetWordData> data;

    /**
     * 获取汉字字典数据列表。
     *
     * @return 包含汉字字典数据的列表。
     */
    public List<GetWordData> getData() {
        return data;
    }

    /**
     * 汉字字典数据类，包含单个汉字的详细信息。
     */
    public static class GetWordData {
        /**
         * 汉字。
         */
        private String word;

        /**
         * 汉字的拼音。
         */
        private String pinyin;

        /**
         * 汉字的部首。
         */
        private String radical;

        /**
         * 汉字的笔画数。
         */
        private String strokes;

        /**
         * 汉字的释义。
         */
        private String explanation;

        /**
         * 汉字的古字形式（如果有）。
         */
        private String old_word;

        /**
         * 获取汉字。
         *
         * @return 汉字。
         */
        public String getWord() {
            return word;
        }

        /**
         * 获取汉字的拼音。
         *
         * @return 汉字的拼音。
         */
        public String getPinyin() {
            return pinyin;
        }

        /**
         * 获取汉字的部首。
         *
         * @return 汉字的部首。
         */
        public String getRadical() {
            return radical;
        }

        /**
         * 获取汉字的笔画数。
         *
         * @return 汉字的笔画数。
         */
        public String getStrokes() {
            return strokes;
        }

        /**
         * 获取汉字的释义。
         *
         * @return 汉字的释义。
         */
        public String getExplanation() {
            return explanation;
        }

        /**
         * 获取汉字的古字形式（如果有）。
         *
         * @return 汉字的古字形式。
         */
        public String getOldWord() {
            return old_word;
        }
    }

    /**
     * 使用 Gson 解析汉字字典的 JSON 数据。
     *
     * @param jsonString JSON 字符串。
     * @return 解析后的 JSONGetWord 对象。
     */
    public static JSONGetWord parseJSON(String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetWord.class);
    }
}
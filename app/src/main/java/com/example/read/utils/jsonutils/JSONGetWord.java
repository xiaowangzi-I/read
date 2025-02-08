package com.example.read.utils.jsonutils;

import com.google.gson.Gson;

import java.util.List;

public class JSONGetWord {

    private List<GetWordData> data;

    public List<GetWordData> getData() {
        return data;
    }
    public static class GetWordData {

        private String word;
        private String pinyin;
        private String radical;
        private String strokes;
        private String explanation;
        private String old_word;

        public String getWord() {
            return word;
        }

        public String getPinyin() {
            return pinyin;
        }

        public String getRadical() {
            return radical;
        }

        public String getStrokes() {
            return strokes;
        }

        public String getExplanation() {
            return explanation;
        }

        public String getOldWord() {
            return old_word;
        }
    }

    public static JSONGetWord parseJSON (String jsonString) {
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JSONGetWord.class);
    }
}

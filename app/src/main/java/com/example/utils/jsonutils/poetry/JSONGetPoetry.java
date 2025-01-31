package com.example.utils.jsonutils.poetry;

import java.util.List;



public class GetPoetry {
    private int code;
    private String msg;
    private Result result;

    public Result getResult() {
        return result;
    }

    public static class Result {
        private List<Poetry> list;

        public List<Poetry> getList() {
            return list;
        }


        public static class Poetry {
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
}

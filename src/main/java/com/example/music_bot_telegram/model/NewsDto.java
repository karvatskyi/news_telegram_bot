package com.example.music_bot_telegram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewsDto {
    private String status;
    private List<Article> articles;
    private int totalResults;

    @Data
    public static class Article {
        private Source source;
        private String author;
        private String title;
        private String description;
        private String url;
        private String urlToImage;
        private String publishedAt;
        private String content;
    }

    @Data
    public static class Source {
        private String id;
        private String name;
    }
}
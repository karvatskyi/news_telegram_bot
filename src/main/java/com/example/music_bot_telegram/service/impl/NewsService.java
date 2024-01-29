package com.example.music_bot_telegram.service.impl;

import com.example.music_bot_telegram.model.NewsDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
public class NewsService {

    @Value("${news.token}")
    private String token;

    public List<NewsDto> getNewsByCountry(String baseUrl, String country) {
        String url = baseUrl.formatted(country, token);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            return parse(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<NewsDto> getNewsBySource(String baseUrl, String source) {
        String url = baseUrl.formatted(source, token);
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            return parse(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<NewsDto> parse(HttpResponse<String> input) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<NewsDto> result = new LinkedList<>();
        try {
            JsonNode root = objectMapper.readTree(input.body());
            JsonNode articlesNode = root.get("articles");

            if (articlesNode != null && articlesNode.isArray()) {
                for (JsonNode articleNode : articlesNode) {
                    NewsDto.Article article = objectMapper.treeToValue(articleNode, NewsDto.Article.class);
                    NewsDto newsDto = new NewsDto();
                    newsDto.setArticles(Collections.singletonList(article));
                    result.add(newsDto);
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't parse response to object", e);
        }
        return result;
    }
}

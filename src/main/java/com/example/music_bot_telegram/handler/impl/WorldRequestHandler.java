package com.example.music_bot_telegram.handler.impl;

import com.example.music_bot_telegram.constant.Constants;
import com.example.music_bot_telegram.handler.UserRequestHandler;
import com.example.music_bot_telegram.helper.KeyboardHelper;
import com.example.music_bot_telegram.model.NewsDto;
import com.example.music_bot_telegram.service.TelegramService;
import com.example.music_bot_telegram.service.impl.NewsService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class WorldRequestHandler extends UserRequestHandler {
    private final TelegramService telegramService;
    private final NewsService newsService;

    public WorldRequestHandler(TelegramService telegramService, NewsService newsService) {
        this.telegramService = telegramService;
        this.newsService = newsService;
    }

    @Override
    public boolean isApplicable(Update request) {
        return isTextMessage(request, Constants.BUTTON_WORLD_NEWS);
    }

    @Override
    public void handle(Update dispatchRequest) {
        List<NewsDto> newsList = newsService.getNewsBySource(Constants.URL_SOURCE_NEWS,
                Constants.SOURCE_BBC_NEWS);
        Long chatId = dispatchRequest.getMessage().getChatId();
        for (NewsDto news : newsList) {
            telegramService.sendMessage(chatId, buildMessage(news), KeyboardHelper.buildStartMenuReply());
        }

    }

    private String buildMessage(NewsDto news) {
        NewsDto.Article article = news.getArticles().get(0);
        LocalDateTime publishedDateTime = LocalDateTime.parse(article.getPublishedAt(), DateTimeFormatter.ISO_DATE_TIME);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = publishedDateTime.format(formatter);
        return "Title: " + article.getTitle()
                + "\nAuthor: " + article.getAuthor()
                + "\nDescription: " + article.getDescription()
                +"\nDate: " + formattedDate
                +"\nlink: " + article.getUrl();
    }
}

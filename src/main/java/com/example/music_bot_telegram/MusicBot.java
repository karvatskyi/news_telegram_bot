package com.example.music_bot_telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class MusicBot extends TelegramLongPollingBot {
    private final Dispatcher dispatcher;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    public MusicBot(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText() || update.hasCallbackQuery()) {
            dispatcher.dispatch(update);
        } else {
            System.out.println("Unexpected update from user");
        }
    }
}

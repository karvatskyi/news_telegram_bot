package com.example.music_bot_telegram.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Slf4j
@Component
public class MusicBotSender extends DefaultAbsSender {
    @Value("${bot.token}")
    private String token;

    protected MusicBotSender() {
        super(new DefaultBotOptions());
    }

    @Override
    public String getBotToken() {
        return token;
    }
}

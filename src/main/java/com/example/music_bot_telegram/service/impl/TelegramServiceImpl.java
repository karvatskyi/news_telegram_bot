package com.example.music_bot_telegram.service.impl;

import com.example.music_bot_telegram.sender.MusicBotSender;
import com.example.music_bot_telegram.service.TelegramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Slf4j
@Component
public class TelegramServiceImpl implements TelegramService {
    private final MusicBotSender sender;

    public TelegramServiceImpl(MusicBotSender sender) {
        this.sender = sender;
    }

    @Override
    public void sendMessage(Long chatId, String text, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = SendMessage
                .builder()
                .text(text)
                .chatId(chatId.toString())
                .parseMode(ParseMode.HTML)
                .replyMarkup(replyKeyboard)
                .build();
        execute(sendMessage);
    }
    @Override
    public void sendMessage(Long chatId, String text, InlineKeyboardMarkup replyKeyboard) {
        SendMessage sendMessage = SendMessage
                .builder()
                .text(text)
                .chatId(chatId.toString())
                .replyMarkup(replyKeyboard)
                .build();
        execute(sendMessage);
    }

    @Override
    public void sendMessage(Long chatId, String text) {
        sendMessage(chatId, text, null);
    }

    private void execute(BotApiMethod botApiMethod) {
        try {
            sender.execute(botApiMethod);
        } catch (Exception e) {
            throw new RuntimeException("Exception: ", e);
        }
    }
}

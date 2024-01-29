package com.example.music_bot_telegram.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface TelegramService {
    void sendMessage(Long chatId, String text, ReplyKeyboard replyKeyboard);
    void sendMessage(Long chatId, String text);
    void sendMessage(Long chatId, String text, InlineKeyboardMarkup replyKeyboard);
}

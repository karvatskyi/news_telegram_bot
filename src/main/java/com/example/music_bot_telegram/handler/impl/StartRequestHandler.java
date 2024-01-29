package com.example.music_bot_telegram.handler.impl;

import com.example.music_bot_telegram.constant.Constants;
import com.example.music_bot_telegram.handler.UserRequestHandler;
import com.example.music_bot_telegram.helper.KeyboardHelper;
import com.example.music_bot_telegram.service.TelegramService;
import com.example.music_bot_telegram.service.impl.NewsService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@Component
public class StartRequestHandler extends UserRequestHandler {
    private final TelegramService telegramService;

    public StartRequestHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(Update request) {
        return isCommand(request, Constants.COMMAND_START);
    }

    @Override
    public void handle(Update request) {
        ReplyKeyboard replyKeyboardMarkup = KeyboardHelper.buildStartMenuReply();
        telegramService.sendMessage(request.getMessage().getChatId()
                , "UKRAINE NEWS\uD83D\uDD25"
                        + "\n"
                        + "\nStay informed with the latest updates! UKRAINE NEWS delivers breaking news right to "
                        + "your fingertips. Get instant access to current events, top stories, and trending"
                        + " topics. "
                        + "\n"
                        + "\nStay ahead, stay connected! ❤️",
                replyKeyboardMarkup
                );
    }
}

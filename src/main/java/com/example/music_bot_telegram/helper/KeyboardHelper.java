package com.example.music_bot_telegram.helper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import java.util.List;

@Component
public class KeyboardHelper {

    public static ReplyKeyboardMarkup buildStartMenuReply() {
        List<KeyboardButton> buttons = List.of(
                new KeyboardButton("UKRAINE NEWS"),
                new KeyboardButton("WORLD_NEWS"));
        KeyboardRow row = new KeyboardRow(buttons);
        return ReplyKeyboardMarkup.builder()
                .keyboard(List.of(row))
                .selective(true)
                .resizeKeyboard(true)
                .oneTimeKeyboard(false)
                .build();
    }
}

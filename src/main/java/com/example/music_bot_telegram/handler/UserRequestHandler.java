package com.example.music_bot_telegram.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class UserRequestHandler {
    public abstract boolean isApplicable(Update request);
    public abstract void handle(Update dispatchRequest);
    public boolean isCommand(Update update, String command) {
        return update.hasMessage() && update.getMessage().isCommand()
                && update.getMessage().getText().equals(command);
    }
    public boolean isTextMessage(Update update) {
        return update.hasMessage() && update.getMessage().hasText();
    }

    public boolean isTextMessage(Update update, String text) {
        return update.hasMessage() && update.getMessage().hasText() && update.getMessage().getText().equals(text);
    }
}

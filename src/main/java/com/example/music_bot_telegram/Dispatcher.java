package com.example.music_bot_telegram;

import com.example.music_bot_telegram.handler.UserRequestHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class Dispatcher {
    private final List<UserRequestHandler> handlers;

    public Dispatcher(List<UserRequestHandler> handlers) {
        this.handlers = handlers;
    }
    public boolean dispatch(Update userRequest) {
        for (UserRequestHandler userRequestHandler : handlers) {
            if (userRequestHandler.isApplicable(userRequest)) {
                userRequestHandler.handle(userRequest);
                return true;
            }
        }
        return false;
    }
}

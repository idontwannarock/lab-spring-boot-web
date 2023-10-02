package com.example.lab.spring.boot.mvc.spring.config.converter;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class ChatroomStatusConverter implements Converter<Integer, ChatroomStatus> {

    @Override
    public ChatroomStatus convert(@NonNull Integer source) {
        return ChatroomStatus.of(source);
    }
}

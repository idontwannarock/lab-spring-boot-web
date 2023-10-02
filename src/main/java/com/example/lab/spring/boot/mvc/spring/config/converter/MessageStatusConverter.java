package com.example.lab.spring.boot.mvc.spring.config.converter;

import com.example.lab.spring.boot.mvc.app.domain.MessageStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class MessageStatusConverter implements Converter<Integer, MessageStatus> {

    @Override
    public MessageStatus convert(@NonNull Integer source) {
        return MessageStatus.of(source);
    }
}

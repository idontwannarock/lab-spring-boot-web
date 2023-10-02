package com.example.lab.spring.boot.mvc.spring.config.converter;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomUserRole;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class ChatroomUserRoleConverter implements Converter<Integer, ChatroomUserRole> {

    @Override
    public ChatroomUserRole convert(@NonNull Integer source) {
        return ChatroomUserRole.of(source);
    }
}

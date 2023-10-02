package com.example.lab.spring.boot.mvc.spring.data.po.converter;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ChatroomStatusConverter implements AttributeConverter<ChatroomStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ChatroomStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.value();
    }

    @Override
    public ChatroomStatus convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return ChatroomStatus.of(dbData);
    }
}

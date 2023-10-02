package com.example.lab.spring.boot.mvc.spring.data.po.converter;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomUserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ChatroomUserRoleConverter implements AttributeConverter<ChatroomUserRole, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ChatroomUserRole attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.value();
    }

    @Override
    public ChatroomUserRole convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return ChatroomUserRole.of(dbData);
    }
}

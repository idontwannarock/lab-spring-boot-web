package com.example.lab.spring.boot.mvc.spring.data.po.converter;

import com.example.lab.spring.boot.mvc.app.domain.MessageStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MessageStatusConverter implements AttributeConverter<MessageStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MessageStatus attribute) {
        return attribute.value();
    }

    @Override
    public MessageStatus convertToEntityAttribute(Integer dbData) {
        return MessageStatus.of(dbData);
    }
}

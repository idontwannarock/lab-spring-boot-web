package com.example.lab.spring.boot.mvc.app.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MessageStatus {

    DELETED(0),
    ACTIVE(1);

    private final int value;

    @JsonValue
    public int value() {
        return this.value;
    }

    @JsonCreator
    public static MessageStatus of(Integer value) {
        if (value == null) {
            return null;
        }
        for (MessageStatus target : MessageStatus.values()) {
            if (target.value == value) {
                return target;
            }
        }
        return null;
    }
}

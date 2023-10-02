package com.example.lab.spring.boot.mvc.spring.controller.response;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomStatus;
import com.example.lab.spring.boot.mvc.app.domain.MessageStatus;

import java.time.Instant;

public record MessageResponse(
        Long id,
        Long chatroomId,
        ChatroomStatus chatroomStatus,
        String content,
        MessageStatus status,
        Integer creatorId,
        Instant createTime
) {}

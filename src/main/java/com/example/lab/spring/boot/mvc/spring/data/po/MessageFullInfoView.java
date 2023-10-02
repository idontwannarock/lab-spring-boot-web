package com.example.lab.spring.boot.mvc.spring.data.po;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomStatus;
import com.example.lab.spring.boot.mvc.app.domain.MessageStatus;

import java.time.Instant;

public record MessageFullInfoView(

        Long id,
        Long chatroomId,
        ChatroomStatus chatroomStatus,
        String content,
        MessageStatus status,
        Integer creatorId,
        Instant createTime
) {}

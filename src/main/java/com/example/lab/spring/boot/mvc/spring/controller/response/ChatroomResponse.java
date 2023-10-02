package com.example.lab.spring.boot.mvc.spring.controller.response;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomStatus;

import java.time.Instant;

public record ChatroomResponse(
        Long id,
        ChatroomStatus status,
        Integer creatorId,
        Instant createTime
) {}

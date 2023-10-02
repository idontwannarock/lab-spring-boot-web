package com.example.lab.spring.boot.mvc.app.usecase;

import jakarta.validation.constraints.NotNull;

public interface CreateMessageUseCase {

    Long create(@NotNull Integer userId, @NotNull Long chatroomId, String content);
}

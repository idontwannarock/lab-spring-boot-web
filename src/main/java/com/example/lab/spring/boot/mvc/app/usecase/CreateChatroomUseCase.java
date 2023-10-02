package com.example.lab.spring.boot.mvc.app.usecase;

import jakarta.validation.constraints.NotNull;

public interface CreateChatroomUseCase {

    Long handle(@NotNull Integer userId);
}

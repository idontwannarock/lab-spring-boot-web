package com.example.lab.spring.boot.mvc.app.usecase.impl;

import com.example.lab.spring.boot.mvc.app.gateway.ChatroomDataGateway;
import com.example.lab.spring.boot.mvc.app.usecase.CreateChatroomUseCase;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Validated
@Service
public class CreateChatroomUseCaseImpl implements CreateChatroomUseCase {

    private final ChatroomDataGateway chatroomDataGateway;

    @Override
    public Long handle(@NotNull Integer userId) {
        return chatroomDataGateway.createChatroom(userId);
    }
}

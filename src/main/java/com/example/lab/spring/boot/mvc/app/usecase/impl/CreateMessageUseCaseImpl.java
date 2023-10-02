package com.example.lab.spring.boot.mvc.app.usecase.impl;

import com.example.lab.spring.boot.mvc.app.gateway.MessageDataGateway;
import com.example.lab.spring.boot.mvc.app.usecase.CreateMessageUseCase;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateMessageUseCaseImpl implements CreateMessageUseCase {

    private final MessageDataGateway messageDataGateway;

    @Override
    public Long create(@NotNull Integer creatorId, @NotNull Long chatroomId, String content) {
        return messageDataGateway.createMessage(creatorId, chatroomId, content);
    }
}

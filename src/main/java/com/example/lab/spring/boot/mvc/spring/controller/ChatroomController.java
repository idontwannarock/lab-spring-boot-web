package com.example.lab.spring.boot.mvc.spring.controller;

import com.example.lab.spring.boot.mvc.app.usecase.CreateChatroomUseCase;
import com.example.lab.spring.boot.mvc.app.usecase.CreateMessageUseCase;
import com.example.lab.spring.boot.mvc.spring.config.security.dto.AuthenticatedUser;
import com.example.lab.spring.boot.mvc.spring.controller.aop.annotation.ChatroomExists;
import com.example.lab.spring.boot.mvc.spring.controller.aop.annotation.UserExists;
import com.example.lab.spring.boot.mvc.spring.controller.mapper.ChatroomMapper;
import com.example.lab.spring.boot.mvc.spring.controller.mapper.MessageMapper;
import com.example.lab.spring.boot.mvc.spring.controller.request.CreateMessageRequest;
import com.example.lab.spring.boot.mvc.spring.controller.response.ChatroomResponse;
import com.example.lab.spring.boot.mvc.spring.controller.response.MessageResponse;
import com.example.lab.spring.boot.mvc.spring.controller.response.ResponsePayload;
import com.example.lab.spring.boot.mvc.spring.repository.ChatroomRepository;
import com.example.lab.spring.boot.mvc.spring.repository.MessageRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RequestMapping(path = "api/v1/chatrooms")
@RestController
public class ChatroomController {

    private final ChatroomRepository chatroomRepository;
    private final MessageRepository messageRepository;

    private final ChatroomMapper chatroomMapper;
    private final MessageMapper messageMapper;

    private final CreateChatroomUseCase createChatroomUseCase;

    @UserExists
    @PostMapping
    ResponsePayload<Long> createChatroom(
            @AuthenticationPrincipal AuthenticatedUser currentUser) {
        return ResponsePayload.success(createChatroomUseCase.handle(currentUser.getUserId()));
    }

    @UserExists
    @GetMapping
    ResponsePayload<List<ChatroomResponse>> findAllChatrooms(
            @AuthenticationPrincipal AuthenticatedUser currentUser) {
        return ResponsePayload.success(chatroomRepository.findAll(currentUser.getUserId())
                .stream()
                .map(chatroomMapper::toResponse)
                .toList());
    }

    private final CreateMessageUseCase createMessageUseCase;

    @ChatroomExists
    @PostMapping(path = "{chatroomId}/messages")
    ResponsePayload<Long> createMessage(
            @AuthenticationPrincipal AuthenticatedUser currentUser,
            @NotNull @PathVariable Long chatroomId,
            @Valid @RequestBody CreateMessageRequest request) {
        return ResponsePayload.success(createMessageUseCase.create(currentUser.getUserId(), chatroomId, request.getContent()));
    }

    @ChatroomExists
    @GetMapping(path = "{chatroomId}/messages")
    ResponsePayload<List<MessageResponse>> findMessagesById(
            @AuthenticationPrincipal AuthenticatedUser ignoreUser,
            @NotNull @PathVariable Long chatroomId) {
        return ResponsePayload.success(messageRepository.findAllByChatroomId(chatroomId)
                .stream()
                .map(messageMapper::toResponse)
                .toList());
    }
}

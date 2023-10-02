package com.example.lab.spring.boot.mvc.spring.controller.aop;

import com.example.lab.spring.boot.mvc.spring.repository.ChatroomRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;

@RequiredArgsConstructor
@Order(20)
@Component
@Aspect
public class ChatroomExistsAspect {

    private final ChatroomRepository chatroomRepository;

    @Pointcut("""
	@annotation(com.example.lab.spring.boot.mvc.spring.controller.aop.annotation.ChatroomExists)
	 && args(*,chatroomId,..)""")
    public void pointcut(Long chatroomId) {}

    @Before(value = "pointcut(chatroomId)", argNames = "chatroomId")
    public void checkIsChatroomExists(@NotNull Long chatroomId) {
        if (!chatroomRepository.existsById(chatroomId)) {
            throw new ServerWebInputException("chatroom not found");
        }
    }
}

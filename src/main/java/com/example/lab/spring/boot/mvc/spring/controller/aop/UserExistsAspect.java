package com.example.lab.spring.boot.mvc.spring.controller.aop;

import com.example.lab.spring.boot.mvc.spring.config.security.dto.AuthenticatedUser;
import com.example.lab.spring.boot.mvc.spring.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebInputException;

@RequiredArgsConstructor
@Order(10)
@Component
@Aspect
class UserExistsAspect {

    private final UserRepository userRepository;

    @Pointcut("""
    (@annotation(com.example.lab.spring.boot.mvc.spring.controller.aop.annotation.ChatroomExists)
    || @annotation(com.example.lab.spring.boot.mvc.spring.controller.aop.annotation.UserExists))
    && args(currentUser,..)""")
    public void pointcut(AuthenticatedUser currentUser) {}

    @Before(value = "pointcut(currentUser)", argNames = "currentUser")
    public void checkIsUserExists(@NotNull AuthenticatedUser currentUser) {
        if (currentUser.getUserId() == null) {
            throw new ServerWebInputException("user id is required in jwt");
        }
        if (!userRepository.existsById(currentUser.getUserId())) {
            throw new ServerWebInputException("user not found");
        }
    }
}

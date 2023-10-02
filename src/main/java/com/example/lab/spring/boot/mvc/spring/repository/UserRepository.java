package com.example.lab.spring.boot.mvc.spring.repository;

import com.example.lab.spring.boot.mvc.spring.data.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRepository {

    private final UserDao userDao;

    public boolean existsById(Integer userId) {
        return userDao.existsByUserId(userId);
    }
}

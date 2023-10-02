package com.example.lab.spring.boot.mvc.spring.data.dao;

import com.example.lab.spring.boot.mvc.spring.data.po.UserPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserPo, Long> {

    boolean existsByUserId(Integer userId);
}

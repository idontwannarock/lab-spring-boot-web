package com.example.lab.spring.boot.mvc.spring.data.dao;

import com.example.lab.spring.boot.mvc.spring.data.po.ChatroomUserPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatroomUserDao extends JpaRepository<ChatroomUserPo, Integer> {

    Optional<ChatroomUserPo> findByChatroomIdAndUserId(Long chatroomId, Integer userId);
}

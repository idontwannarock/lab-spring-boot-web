package com.example.lab.spring.boot.mvc.spring.data.dao;

import com.example.lab.spring.boot.mvc.spring.data.po.ChatroomPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChatroomDao extends JpaRepository<ChatroomPo, Long> {

    List<ChatroomPo> findAllByCreatorId(Integer userId);

    @Query(value = """
	SELECT c.*
	 FROM CHATROOM c
	 INNER JOIN CHATROOM_USER cu
	 ON c.ID = cu.CHATROOM_ID
	 WHERE c.ID = :chatroomId AND cu.USER_ID = :userId""", nativeQuery = true)
    Optional<ChatroomPo> findByChatroomIdAndChatroomUserId(Long chatroomId, Integer userId);
}

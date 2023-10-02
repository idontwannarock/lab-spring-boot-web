package com.example.lab.spring.boot.mvc.spring.data.dao;

import com.example.lab.spring.boot.mvc.spring.data.po.MessageFullInfoView;
import com.example.lab.spring.boot.mvc.spring.data.po.MessagePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageDao extends JpaRepository<MessagePo, Long> {

    @Query(value = """
	SELECT m.*, c.STATUS AS chatroomStatus
	 FROM MESSAGE m
	 INNER JOIN CHATROOM c ON m.CHATROOM_ID = c.ID
	 WHERE c.ID = :chatroomId""", nativeQuery = true)
    List<MessageFullInfoView> findAllFullViewByChatroomId(Long chatroomId);
}

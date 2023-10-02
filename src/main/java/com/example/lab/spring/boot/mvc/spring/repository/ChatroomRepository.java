package com.example.lab.spring.boot.mvc.spring.repository;

import com.example.lab.spring.boot.mvc.app.domain.ChatroomStatus;
import com.example.lab.spring.boot.mvc.app.domain.ChatroomUserRole;
import com.example.lab.spring.boot.mvc.app.gateway.ChatroomDataGateway;
import com.example.lab.spring.boot.mvc.spring.data.dao.ChatroomDao;
import com.example.lab.spring.boot.mvc.spring.data.dao.ChatroomUserDao;
import com.example.lab.spring.boot.mvc.spring.data.po.ChatroomPo;
import com.example.lab.spring.boot.mvc.spring.data.po.ChatroomUserPo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ChatroomRepository implements ChatroomDataGateway {

    private final ChatroomDao chatroomDao;
    private final ChatroomUserDao chatroomUserDao;

    @Transactional
    @Override
    public Long createChatroom(Integer userId) {
        var chatroom = new ChatroomPo();
        chatroom.setStatus(ChatroomStatus.ACTIVE);
        chatroom.setCreatorId(userId);
        chatroomDao.save(chatroom);

        var chatroomUser = new ChatroomUserPo();
        chatroomUser.setChatroomId(chatroom.getId());
        chatroomUser.setUserId(chatroom.getCreatorId());
        chatroomUser.setRole(ChatroomUserRole.OWNER);
        chatroomUserDao.save(chatroomUser);

        return chatroom.getId();
    }

    public List<ChatroomPo> findAll(Integer userId) {
        return chatroomDao.findAllByCreatorId(userId);
    }

    public boolean existsById(Long chatroomId) {
        return chatroomDao.existsById(chatroomId);
    }
}

package com.example.lab.spring.boot.mvc.spring.repository;

import com.example.lab.spring.boot.mvc.app.domain.MessageStatus;
import com.example.lab.spring.boot.mvc.app.gateway.MessageDataGateway;
import com.example.lab.spring.boot.mvc.spring.data.dao.MessageDao;
import com.example.lab.spring.boot.mvc.spring.data.po.MessageFullInfoView;
import com.example.lab.spring.boot.mvc.spring.data.po.MessagePo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Component
public class MessageRepository implements MessageDataGateway {

    private final MessageDao messageDao;

    @Transactional
    @Override
    public Long createMessage(Integer creatorId, Long chatroomId, String content) {
        var message = new MessagePo();
        message.setChatroomId(chatroomId);
        message.setContent(content);
        message.setStatus(MessageStatus.ACTIVE);
        message.setCreatorId(creatorId);
        message.setCreateTime(Instant.now());
        return messageDao.save(message).getId();
    }

    public List<MessageFullInfoView> findAllByChatroomId(Long chatroomId) {
        return messageDao.findAllFullViewByChatroomId(chatroomId);
    }
}

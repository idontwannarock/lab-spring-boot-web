package com.example.lab.spring.boot.mvc.app.gateway;

public interface MessageDataGateway {

    Long createMessage(Integer creatorId, Long chatroomId, String content);
}

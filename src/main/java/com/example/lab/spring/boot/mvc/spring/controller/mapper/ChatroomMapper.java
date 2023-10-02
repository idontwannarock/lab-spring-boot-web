package com.example.lab.spring.boot.mvc.spring.controller.mapper;

import com.example.lab.spring.boot.mvc.spring.controller.response.ChatroomResponse;
import com.example.lab.spring.boot.mvc.spring.data.po.ChatroomPo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatroomMapper {

    ChatroomResponse toResponse(ChatroomPo chatroom);
}

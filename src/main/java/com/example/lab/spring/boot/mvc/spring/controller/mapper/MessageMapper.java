package com.example.lab.spring.boot.mvc.spring.controller.mapper;

import com.example.lab.spring.boot.mvc.spring.controller.response.MessageResponse;
import com.example.lab.spring.boot.mvc.spring.data.po.MessageFullInfoView;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {

    MessageResponse toResponse(MessageFullInfoView messageView);
}

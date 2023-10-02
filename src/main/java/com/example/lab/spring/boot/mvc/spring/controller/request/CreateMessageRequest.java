package com.example.lab.spring.boot.mvc.spring.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateMessageRequest {

    @NotEmpty
    private String content;
}

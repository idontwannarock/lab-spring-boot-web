package com.example.lab.spring.boot.mvc.spring.config.security.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Builder
@Data
public class AuthenticatedUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 8770603498542002984L;

    private Integer userId;
    private String userName;
}

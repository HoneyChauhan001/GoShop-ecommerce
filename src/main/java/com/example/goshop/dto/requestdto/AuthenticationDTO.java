package com.example.goshop.dto.requestdto;

import lombok.Data;

@Data
public class AuthenticationDTO {
    private String email;

    private String password;
}

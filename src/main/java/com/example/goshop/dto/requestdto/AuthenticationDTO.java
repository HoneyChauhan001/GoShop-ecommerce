package com.example.goshop.dto.requestdto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationDTO {
    private String email;

    private String password;
}

package com.example.goshop.service;

import com.example.goshop.dto.requestdto.AuthenticationDTO;
import com.example.goshop.dto.responsedto.AuthenticationResponseDTO;
import com.example.goshop.exception.UserIsDisabledException;
import com.example.goshop.exception.WrongCredentialsException;

public interface AuthenticationService {
    public AuthenticationResponseDTO createJWTToken(AuthenticationDTO authenticationDTO) throws WrongCredentialsException, UserIsDisabledException;
}

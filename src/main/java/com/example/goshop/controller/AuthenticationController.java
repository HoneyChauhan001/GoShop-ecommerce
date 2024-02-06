package com.example.goshop.controller;

import com.example.goshop.dto.requestdto.AuthenticationDTO;
import com.example.goshop.dto.responsedto.AuthenticationResponseDTO;
import com.example.goshop.exception.UserIsDisabledException;
import com.example.goshop.exception.WrongCredentialsException;
import com.example.goshop.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;


    @PostMapping("/authenticate")
    public ResponseEntity createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws IOException {
        try {
            AuthenticationResponseDTO authenticationResponseDTO = authenticationService.createJWTToken(authenticationDTO);
            return new ResponseEntity<>(authenticationResponseDTO, HttpStatus.OK);
        } catch (WrongCredentialsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UserIsDisabledException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (UsernameNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

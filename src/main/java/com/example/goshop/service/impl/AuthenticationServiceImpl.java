package com.example.goshop.service.impl;

import com.example.goshop.config.JwtTokenUtil;
import com.example.goshop.dto.requestdto.AuthenticationDTO;
import com.example.goshop.dto.responsedto.AuthenticationResponseDTO;
import com.example.goshop.exception.UserIsDisabledException;
import com.example.goshop.exception.WrongCredentialsException;
import com.example.goshop.service.AuthenticationService;
import io.jsonwebtoken.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
        @Autowired
        private JwtTokenUtil jwtTokenUtil;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private CustomerDetailsServiceImpl userDetailsService;

        @Override
        public AuthenticationResponseDTO createJWTToken(AuthenticationDTO authenticationDTO) throws UsernameNotFoundException, IOException, WrongCredentialsException, UserIsDisabledException {
            try{
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword()));
                final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());
                final String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());
                return new AuthenticationResponseDTO(jwt,userDetails.getUsername());
            }
            catch (BadCredentialsException e){
                throw new WrongCredentialsException("Username or Password Incorrect");
            }
            catch (DisabledException e) {
                throw new UserIsDisabledException("User is Disabled");
            }
        }
}


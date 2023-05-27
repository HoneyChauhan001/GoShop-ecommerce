package com.example.goshop.controller;

import com.example.goshop.dto.requestdto.CustomerRequestDto;
import com.example.goshop.dto.responsedto.CustomerResponseDto;
import com.example.goshop.exception.EmailOrMobNoAlreadyExistException;
import com.example.goshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity addCutsomer(@RequestBody CustomerRequestDto customerRequestDto){
        try{
            CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
            return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
        } catch (EmailOrMobNoAlreadyExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

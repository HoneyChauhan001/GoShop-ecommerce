package com.example.goshop.controller;

import com.example.goshop.dto.requestdto.CustomerRequestDto;
import com.example.goshop.dto.responsedto.CustomerResponseDto;
import com.example.goshop.exception.EmailOrMobNoAlreadyExistException;
import com.example.goshop.model.Customer;
import com.example.goshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/sign-up")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        try{
            CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
            return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
        } catch (EmailOrMobNoAlreadyExistException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity getCustomer(Authentication authentication){
        Customer customer = customerService.getCustomer(authentication.getName());
        return new ResponseEntity(customer,HttpStatus.OK);
    }

    @PostMapping("/updateCustomer")
    public ResponseEntity updateCustomer(@RequestBody CustomerRequestDto customerRequestDto, Authentication authentication){
        String customerEmail = authentication.getName();
        try {
            return new ResponseEntity(customerService.updateCustomer(customerEmail,customerRequestDto),HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

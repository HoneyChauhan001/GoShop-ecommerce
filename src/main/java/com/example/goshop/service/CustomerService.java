package com.example.goshop.service;

import com.example.goshop.dto.requestdto.CustomerRequestDto;
import com.example.goshop.dto.responsedto.CustomerResponseDto;
import com.example.goshop.exception.EmailOrMobNoAlreadyExistException;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws EmailOrMobNoAlreadyExistException;
}

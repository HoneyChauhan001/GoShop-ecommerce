package com.example.goshop.service;

import com.example.goshop.dto.requestdto.CustomerRequestDto;
import com.example.goshop.dto.responsedto.CustomerResponseDto;
import com.example.goshop.exception.EmailOrMobNoAlreadyExistException;
import com.example.goshop.model.Customer;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws EmailOrMobNoAlreadyExistException;

    Customer getCustomer(String emailId);

    CustomerResponseDto updateCustomer(String customerEmail, CustomerRequestDto customerRequestDto) throws Exception;
}

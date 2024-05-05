package com.example.goshop.transformer;

import com.example.goshop.dto.requestdto.AuthenticationDTO;
import com.example.goshop.dto.requestdto.CustomerRequestDto;
import com.example.goshop.dto.responsedto.CustomerResponseDto;
import com.example.goshop.model.Customer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CustomerTransformer {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .emailId(customerRequestDto.getEmailId())
                .mobNo(customerRequestDto.getMobNo())
                .gender(customerRequestDto.getGender())
                .password(new BCryptPasswordEncoder().encode(customerRequestDto.getPassword()))
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
    }

}

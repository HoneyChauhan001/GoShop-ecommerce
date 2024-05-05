package com.example.goshop.service.impl;

import com.example.goshop.dto.requestdto.CustomerRequestDto;
import com.example.goshop.dto.responsedto.CustomerResponseDto;
import com.example.goshop.exception.EmailOrMobNoAlreadyExistException;
import com.example.goshop.model.Cart;
import com.example.goshop.model.Customer;
import com.example.goshop.repository.CustomerRepository;
import com.example.goshop.service.AuthenticationService;
import com.example.goshop.service.CartService;
import com.example.goshop.service.CustomerService;
import com.example.goshop.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CartService cartService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AuthenticationService authenticationService;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws EmailOrMobNoAlreadyExistException {
        //dto to entity
        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
        //create cart
        Cart cart = cartService.createCart();
        cart.setCustomer(customer);
        customer.setCart(cart);

        Customer savedCustomer;
        try {
            savedCustomer = customerRepository.save(customer);
        } catch (Exception e) {
            throw new EmailOrMobNoAlreadyExistException("Customer already exist with Email Id or Mobile Number");
        }

        //prepare response Dto
        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);


    }

    @Override
    public Customer getCustomer(String emailId) {
        Customer customer = customerRepository.findByEmailId(emailId);
        return customer;
    }

    @Override
    public CustomerResponseDto updateCustomer(String customerEmail, CustomerRequestDto customerRequestDto) throws Exception {
        try{
            Customer customer = customerRepository.findByEmailId(customerEmail);
            if(customerRequestDto.getEmailId() != null){
                customer.setEmailId(customerRequestDto.getEmailId());
            }
            if(0 != customerRequestDto.getAge()){
                customer.setAge(customerRequestDto.getAge());
            }
            if(customerRequestDto.getGender() != null){
                customer.setGender(customerRequestDto.getGender());
            }
            if(customerRequestDto.getMobNo() != null){
                customer.setMobNo(customerRequestDto.getMobNo());
            }
            if(customerRequestDto.getName() != null){
                customer.setName(customerRequestDto.getName());
            }
            if(customerRequestDto.getPassword() != null){
                customer.setPassword(new BCryptPasswordEncoder().encode(customerRequestDto.getPassword()));
            }
            return CustomerTransformer.customerToCustomerResponseDto(customerRepository.save(customer));
        } catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    }
}

package com.example.goshop.service.impl;

import com.example.goshop.dto.requestdto.CustomerRequestDto;
import com.example.goshop.dto.responsedto.CustomerResponseDto;
import com.example.goshop.exception.EmailOrMobNoAlreadyExistException;
import com.example.goshop.model.Cart;
import com.example.goshop.model.Customer;
import com.example.goshop.repository.CustomerRepository;
import com.example.goshop.service.CartService;
import com.example.goshop.service.CustomerService;
import com.example.goshop.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CartService cartService;
    @Autowired
    CustomerRepository customerRepository;
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
    public CustomerResponseDto getCustomer(String emailId) {
        Customer customer = customerRepository.findByEmailId(emailId);
        return CustomerTransformer.customerToCustomerResponseDto(customer);
    }
}

package com.example.goshop.service.impl;

import com.example.goshop.dto.requestdto.ItemRequestDto;
import com.example.goshop.exception.CustomerNotPresentException;
import com.example.goshop.exception.InSufficientQuantityException;
import com.example.goshop.exception.OutOfStockException;
import com.example.goshop.exception.ProductNotPresentException;
import com.example.goshop.model.Customer;
import com.example.goshop.model.Item;
import com.example.goshop.model.Product;
import com.example.goshop.repository.CustomerRepository;
import com.example.goshop.repository.ProductRepository;
import com.example.goshop.service.ItemService;
import com.example.goshop.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotPresentException, CustomerNotPresentException, OutOfStockException, InSufficientQuantityException {
        //check product
        Optional<Product> productOpt = productRepository.findById(itemRequestDto.getProductId());
        if(productOpt.isEmpty()){
            throw new ProductNotPresentException("This product is not present");
        }
        //check customer
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());

        if(customer == null){
            throw new CustomerNotPresentException("Customer not present");
        }

        Product product = productOpt.get();

        if(product.getQuantity() == 0){
            throw new OutOfStockException("product out of stock");
        }

        //checking availablity of product
        if(product.getQuantity() < itemRequestDto.getRequireQuantity()){
            throw new InSufficientQuantityException("Insufficient quantity of product");
        }

        //create item
        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto,product,customer);

        return item;

    }
}

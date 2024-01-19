package com.example.goshop.service.impl;

import com.example.goshop.Enum.ProductStatus;
import com.example.goshop.dto.requestdto.OrderRequestDto;
import com.example.goshop.dto.responsedto.OrderResponseDto;
import com.example.goshop.exception.*;
import com.example.goshop.model.*;
import com.example.goshop.repository.CardRepository;
import com.example.goshop.repository.CustomerRepository;
import com.example.goshop.repository.OrderRepository;
import com.example.goshop.repository.ProductRepository;
import com.example.goshop.service.ItemService;
import com.example.goshop.service.OrderService;
import com.example.goshop.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotExistException, ProductNotPresentException, OutOfStockException, InSufficientQuantityException, InvalidCardException {

        //check customer
        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmailId());

        if(customer == null){
            throw new CustomerNotExistException("No customer with this Email");
        }

        //check product
        Optional<Product> productOpt =  productRepository.findById(orderRequestDto.getProductId());
        if(productOpt.isEmpty()){
            throw new ProductNotPresentException("product with this Id is not present");
        }
        Product product = productOpt.get();

        //checking product availability
        if(product.getProductStatus() == ProductStatus.OUT_OF_STOCK){
            throw new OutOfStockException("product is out of stock");
        }
        if(product.getQuantity()< orderRequestDto.getQuantity()){
            throw new InSufficientQuantityException("Insufficient quantity of product");
        }

        //checking card validity
        Date date = new Date();
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card == null || card.getCvv()!=orderRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Invalid Card");
        }

        //decrease product quantity
        int newQuantity = product.getQuantity() - orderRequestDto.getQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity == 0)product.setProductStatus(ProductStatus.OUT_OF_STOCK);

        //create Item
        Item item = itemService.createItem(orderRequestDto.getQuantity());
        item.setProduct(product);

        //OrderRequestDto to entity
        String maskedCard = generateMaskedCardNo(card);
        OrderEntity orderEntity = OrderTransformer.orderRequestDtoToOrder(item,customer,maskedCard);
        orderEntity.getItems().add(item);
        item.setOrderEntity(orderEntity);

        //saving order
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        //adding in corrosponding entities
        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        //prepare response Dto
        return OrderTransformer.orderToOrderResponseDto(orderEntity);
    }

    public String generateMaskedCardNo(Card card){
        String cardNo = card.getCardNo();
        StringBuilder sb = new StringBuilder();
        int n = cardNo.length();

        for(int i=0; i<n-4; i++){
            sb.append("X");
        }
        sb.append(cardNo.substring(n-4));

        return sb.toString();
    }
}

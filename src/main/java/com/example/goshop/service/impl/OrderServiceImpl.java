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
import com.example.goshop.service.CardService;
import com.example.goshop.service.ItemService;
import com.example.goshop.service.OrderService;
import com.example.goshop.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemService itemService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CardService cardService;
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
        Optional<Card> cardOpt = cardService.isCardValid(orderRequestDto.getCardNo(),orderRequestDto.getCvv());
        if(cardOpt.isEmpty()){
            throw new InvalidCardException("Invalid card !!");
        }
        Card card = cardOpt.get();

        //decrease product quantity
        int newQuantity = product.getQuantity() - orderRequestDto.getQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity == 0)product.setProductStatus(ProductStatus.OUT_OF_STOCK);

        //create Item
        Item item = itemService.createItem(orderRequestDto.getQuantity());
        item.setProduct(product);

        //OrderRequestDto to entity
        String maskedCard = cardService.generateMaskedCardNo(card);
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

    @Override
    public OrderEntity placeOrder(Cart cart, Card card) throws InSufficientQuantityException {
        //create orderEntity
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.generateMaskedCardNo(card));

        int totalValue = 0;
        try{
            for(Item item : cart.getItems()){
                Product product = item.getProduct();
                if(item.getRequiredQuantity()>product.getQuantity()){
                    throw new InSufficientQuantityException("insufficient quantity of product " + product.getName());
                }
                totalValue += item.getRequiredQuantity()*product.getPrice();
                int newQuantity = product.getQuantity()-item.getRequiredQuantity();
                product.setQuantity(newQuantity);
                if(product.getQuantity() == 0){
                    product.setProductStatus(ProductStatus.OUT_OF_STOCK);
                }
                item.setOrderEntity(orderEntity);
            }
        } catch (InSufficientQuantityException e){
            for(Item item : cart.getItems()) {
                if (item.getOrderEntity() != null) {
                    Product product = item.getProduct();
                    item.setOrderEntity(null);
                    int originalQuantity = product.getQuantity() + item.getRequiredQuantity();
                    product.setQuantity(originalQuantity);
                    if (originalQuantity > 0) {
                        product.setProductStatus(ProductStatus.AVAILABLE);
                    }
                }
            }
            throw e;
        }

        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;


    }
}

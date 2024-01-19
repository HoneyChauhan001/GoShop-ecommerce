package com.example.goshop.service.impl;

import com.example.goshop.dto.requestdto.CheckOutRequestDto;
import com.example.goshop.dto.responsedto.CartResponseDto;
import com.example.goshop.dto.responsedto.OrderResponseDto;
import com.example.goshop.exception.CartEmptyException;
import com.example.goshop.exception.CustomerNotExistException;
import com.example.goshop.exception.InSufficientQuantityException;
import com.example.goshop.exception.InvalidCardException;
import com.example.goshop.model.*;
import com.example.goshop.repository.CartRepository;
import com.example.goshop.repository.CustomerRepository;
import com.example.goshop.repository.OrderRepository;
import com.example.goshop.service.CardService;
import com.example.goshop.service.CartService;
import com.example.goshop.service.OrderService;
import com.example.goshop.transformer.CartTransformer;
import com.example.goshop.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardService cardService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Cart createCart() {
        return CartTransformer.createCart();
    }

    @Override
    public CartResponseDto addToCart(Item item) {

        Cart cart = item.getCart();
        Product product = item.getProduct();

        //update CartTotal
        int newTotal = cart.getCartTotal() + item.getRequiredQuantity()*product.getPrice();
        cart.setCartTotal(newTotal);

        //add item to cart
        cart.getItems().add(item);

        //save cart
        Cart savedCart = cartRepository.save(cart);
        int size = savedCart.getItems().size();

        Item savedItem = savedCart.getItems().get(size-1);
        //add saved item to product
        product.getItems().add(savedItem);

        //prepare response dto
        return CartTransformer.cartToCartResponseDto(savedCart);

    }

    @Override
    public OrderResponseDto checkOut(CheckOutRequestDto checkOutRequestDto) throws CustomerNotExistException, InvalidCardException, CartEmptyException, InSufficientQuantityException {
        //check customer
        Customer customer = customerRepository.findByEmailId(checkOutRequestDto.getCustomerEmailId());
        if(customer == null){
            throw new CustomerNotExistException("customer with this Id not present");
        }

        //check card validity
        Optional<Card> cardOpt = cardService.isCardValid(checkOutRequestDto.getCardNo(),checkOutRequestDto.getCvv());
        if(cardOpt.isEmpty()){
            throw new InvalidCardException("Invalid card !!");
        }
        Card card = cardOpt.get();

        Cart cart = customer.getCart();

        //checking cart is not empty
        if(cart.getItems().size()==0){
            throw new CartEmptyException("cart is empty");
        }

        //creating orderEntity from cart
        try{
            OrderEntity orderEntity = orderService.placeOrder(cart,card);
            resetCart(cart);
            OrderEntity savedOrder = orderRepository.save(orderEntity);
            customer.getOrders().add(savedOrder);

            //prepare Response Entity
            return OrderTransformer.orderToOrderResponseDto(savedOrder);
        }
        catch (InSufficientQuantityException e){
            throw e;
        }
    }

    @Override
    public void resetCart(Cart cart) {
        cart.setCartTotal(0);
        for(Item item : cart.getItems()){
            item.setCart(null);
        }
        //cart.getItems().clear(); -> i can not clear the list as the same list is being used in orderenetity
        cart.setItems(new ArrayList<>());
    }

}

package com.example.goshop.service;

import com.example.goshop.dto.requestdto.OrderRequestDto;
import com.example.goshop.dto.responsedto.OrderResponseDto;
import com.example.goshop.exception.*;
import com.example.goshop.model.Card;
import com.example.goshop.model.Cart;
import com.example.goshop.model.OrderEntity;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotExistException, ProductNotPresentException, OutOfStockException, InSufficientQuantityException, InvalidCardException;
    OrderEntity placeOrder(Cart cart, Card card) throws InSufficientQuantityException;
}

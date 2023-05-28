package com.example.goshop.service;

import com.example.goshop.dto.requestdto.OrderRequestDto;
import com.example.goshop.dto.responsedto.OrderResponseDto;
import com.example.goshop.exception.*;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotExistException, ProductNotPresentException, OutOfStockException, InSufficientQuantityException, InvalidCardException;
}

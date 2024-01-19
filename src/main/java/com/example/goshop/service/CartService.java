package com.example.goshop.service;

import com.example.goshop.dto.requestdto.CheckOutRequestDto;
import com.example.goshop.dto.responsedto.CartResponseDto;
import com.example.goshop.dto.responsedto.OrderResponseDto;
import com.example.goshop.exception.CartEmptyException;
import com.example.goshop.exception.CustomerNotExistException;
import com.example.goshop.exception.InSufficientQuantityException;
import com.example.goshop.exception.InvalidCardException;
import com.example.goshop.model.Cart;
import com.example.goshop.model.Item;

public interface CartService {
    public Cart createCart();

    public CartResponseDto addToCart(Item item);

    OrderResponseDto checkOut(CheckOutRequestDto checkOutRequestDto) throws CustomerNotExistException, InvalidCardException, CartEmptyException, InSufficientQuantityException;
    void resetCart(Cart cart);
}

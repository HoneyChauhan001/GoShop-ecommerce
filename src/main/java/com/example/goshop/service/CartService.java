package com.example.goshop.service;

import com.example.goshop.dto.responsedto.CartResponseDto;
import com.example.goshop.model.Cart;
import com.example.goshop.model.Item;

public interface CartService {
    public Cart createCart();

    public CartResponseDto addToCart(Item item);
}

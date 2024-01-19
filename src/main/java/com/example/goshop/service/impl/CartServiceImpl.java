package com.example.goshop.service.impl;

import com.example.goshop.dto.responsedto.CartResponseDto;
import com.example.goshop.model.Cart;
import com.example.goshop.model.Item;
import com.example.goshop.model.Product;
import com.example.goshop.repository.CartRepository;
import com.example.goshop.service.CartService;
import com.example.goshop.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
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
}

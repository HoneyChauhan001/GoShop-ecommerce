package com.example.goshop.transformer;

import com.example.goshop.dto.responsedto.CartResponseDto;
import com.example.goshop.dto.responsedto.ItemResponseDto;
import com.example.goshop.model.Cart;
import com.example.goshop.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static Cart createCart(){
        return Cart.builder()
                .cartTotal(0)
                .build();
    }

    public static CartResponseDto cartToCartResponseDto(Cart cart){
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item : cart.getItems()){
            itemResponseDtos.add(ItemTransformer.itemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .cartTotal(cart.getCartTotal())
                .items(itemResponseDtos)
                .build();
    }
}

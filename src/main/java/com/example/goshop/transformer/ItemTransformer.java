package com.example.goshop.transformer;

import com.example.goshop.dto.requestdto.ItemRequestDto;
import com.example.goshop.dto.responsedto.ItemResponseDto;
import com.example.goshop.model.Customer;
import com.example.goshop.model.Item;
import com.example.goshop.model.Product;

public class ItemTransformer {

    public static Item itemRequestDtoToItem(ItemRequestDto itemRequestDto, Product product, Customer customer){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequireQuantity())
                .product(product)
                .cart(customer.getCart())
                .build();
    }
    public static Item itemRequestDtoToItem(int quantity){
        return Item.builder()
                .requiredQuantity(quantity)
                .build();
    }


    public static ItemResponseDto itemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .productName(item.getProduct().getName())
                .pricePerItem(item.getProduct().getPrice())
                .itemTotalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .quantity(item.getRequiredQuantity())
                .build();
    }
}

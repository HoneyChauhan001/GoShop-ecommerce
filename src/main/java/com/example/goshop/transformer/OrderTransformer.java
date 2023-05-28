package com.example.goshop.transformer;

import com.example.goshop.dto.responsedto.ItemResponseDto;
import com.example.goshop.dto.responsedto.OrderResponseDto;
import com.example.goshop.model.Customer;
import com.example.goshop.model.Item;
import com.example.goshop.model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity orderRequestDtoToOrder(Item item, Customer customer, String cardUsed){
        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .cardUsed(cardUsed)
                .customer(customer)
                .items(new ArrayList<>())
                .build();
    }

    public static OrderResponseDto orderToOrderResponseDto(OrderEntity orderEntity){
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item : orderEntity.getItems()){
            itemResponseDtos.add(ItemTransformer.itemToItemResponseDto(item));
        }
        return OrderResponseDto.builder()
                .customerName(orderEntity.getCustomer().getName())
                .orderId(orderEntity.getOrderNo())
                .orderTotalValue(orderEntity.getTotalValue())
                .orderDate(orderEntity.getOrderDate())
                .cardUsed(orderEntity.getCardUsed())
                .items(itemResponseDtos)
                .build();
    }
}

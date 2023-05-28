package com.example.goshop.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String customerName;
    String orderId;
    int orderTotalValue;
    Date orderDate;
    String cardUsed;
    List<ItemResponseDto> items;
}

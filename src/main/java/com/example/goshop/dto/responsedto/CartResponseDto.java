package com.example.goshop.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
    String customerName;
    int cartTotal;
    List<ItemResponseDto> items;

}

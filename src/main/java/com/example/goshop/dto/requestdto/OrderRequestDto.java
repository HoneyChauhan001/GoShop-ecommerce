package com.example.goshop.dto.requestdto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {
    String customerEmailId;
    int productId;
    String cardNo;
    int cvv;
    int Quantity;
}

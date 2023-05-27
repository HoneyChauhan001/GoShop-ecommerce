package com.example.goshop.dto.requestdto;

import com.example.goshop.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    String sellerEmailId;
    String name;
    Category category;
    int price;
    int quantity;

}

package com.example.goshop.dto.responsedto;

import com.example.goshop.Enum.Category;
import com.example.goshop.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String productName;
    String sellerName;
    Category category;
    int price;
    ProductStatus productStatus;
}

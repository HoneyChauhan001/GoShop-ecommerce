package com.example.goshop.transformer;

import com.example.goshop.Enum.ProductStatus;
import com.example.goshop.dto.requestdto.ProductRequestDto;
import com.example.goshop.dto.responsedto.ProductResponseDto;
import com.example.goshop.model.Product;

public class ProductTransformer {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        int quantity = productRequestDto.getQuantity();
        return Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(quantity>0?ProductStatus.AVAILABLE:ProductStatus.OUT_OF_STOCK)
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .build();
    }
}

package com.example.goshop.service;

import com.example.goshop.Enum.Category;
import com.example.goshop.dto.requestdto.ProductRequestDto;
import com.example.goshop.dto.responsedto.ProductResponseDto;
import com.example.goshop.exception.SellerDoesNotExistException;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerDoesNotExistException;

    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price);
}

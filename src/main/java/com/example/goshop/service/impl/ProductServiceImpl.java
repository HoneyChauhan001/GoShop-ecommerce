package com.example.goshop.service.impl;

import com.example.goshop.Enum.Category;
import com.example.goshop.dto.requestdto.ProductRequestDto;
import com.example.goshop.dto.responsedto.ProductResponseDto;
import com.example.goshop.exception.SellerDoesNotExistException;
import com.example.goshop.model.Product;
import com.example.goshop.model.Seller;
import com.example.goshop.repository.ProductRepository;
import com.example.goshop.repository.SellerRepository;
import com.example.goshop.service.ProductService;
import com.example.goshop.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductResponseDto addSeller(ProductRequestDto productRequestDto) throws SellerDoesNotExistException {
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());

        if(seller == null){
            throw new SellerDoesNotExistException("Seller does not exist");
        }

        //dto to entity
        Product product = ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        Seller savedSeller = sellerRepository.save(seller);
        int size = savedSeller.getProducts().size();
        Product savedProduct = savedSeller.getProducts().get(size-1);

        //prepare responseDto
        return ProductTransformer.productToProductResponseDto(savedProduct);

    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price) {

        List<Product> products = productRepository.findByCategoryAndPrice(category,price);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = ProductTransformer.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }
}

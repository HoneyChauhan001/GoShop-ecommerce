package com.example.goshop.transformer;

import com.example.goshop.dto.requestdto.SellerRequestDto;
import com.example.goshop.dto.responsedto.SellerResponseDto;
import com.example.goshop.model.Seller;

public class SellerTransformer {
    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .mobNo(seller.getMobNo())
                .build();
    }
}

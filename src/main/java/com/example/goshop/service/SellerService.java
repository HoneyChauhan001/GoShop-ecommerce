package com.example.goshop.service;

import com.example.goshop.dto.requestdto.SellerRequestDto;
import com.example.goshop.dto.responsedto.SellerResponseDto;
import com.example.goshop.exception.EmailOrMobNoAlreadyExistException;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailOrMobNoAlreadyExistException;
}

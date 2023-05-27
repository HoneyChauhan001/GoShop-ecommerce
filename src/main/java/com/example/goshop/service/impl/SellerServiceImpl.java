package com.example.goshop.service.impl;

import com.example.goshop.dto.requestdto.SellerRequestDto;
import com.example.goshop.dto.responsedto.SellerResponseDto;
import com.example.goshop.exception.EmailOrMobNoAlreadyExistException;
import com.example.goshop.model.Seller;
import com.example.goshop.repository.SellerRepository;
import com.example.goshop.service.SellerService;
import com.example.goshop.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailOrMobNoAlreadyExistException {
        //dto -> entity
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
        //save entity
        Seller savedSeller;
        try {
            savedSeller = sellerRepository.save(seller);
        } catch (Exception e) {
            throw new EmailOrMobNoAlreadyExistException("Seller already exist with Email Id or Mobile Number");
        }
        //prepare responseDto
        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }
}

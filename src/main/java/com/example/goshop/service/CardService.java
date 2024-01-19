package com.example.goshop.service;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.exception.CustomerNotExistException;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotExistException;
}

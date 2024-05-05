package com.example.goshop.service;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.exception.CustomerNotExistException;

import java.util.List;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto, String userEmailId) throws Exception;

    List<CardResponseDto> getAllCards(String userEmailId) throws CustomerNotExistException;

    CardResponseDto getById(String userEmailId, CardResponseDto cardRequestDto) throws Exception;
}

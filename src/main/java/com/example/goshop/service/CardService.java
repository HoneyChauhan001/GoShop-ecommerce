package com.example.goshop.service;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.exception.CustomerNotExistException;
import com.example.goshop.model.Card;

import java.util.Optional;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotExistException;
    public Optional<Card> isCardValid(String cardNo, int cvv);
    public String generateMaskedCardNo(Card card);
}

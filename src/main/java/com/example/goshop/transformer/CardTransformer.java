package com.example.goshop.transformer;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.model.Card;

public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .build();
    }

    public static CardResponseDto cardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .build();
    }
}

package com.example.goshop.transformer;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.model.Card;

import java.util.ArrayList;
import java.util.List;

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
                .id(card.getId())
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .validTill(card.getValidTill())
                .build();
    }

    public static List<CardResponseDto> cardToCardResponseDto(List<Card> cards){
        List<CardResponseDto> cardResponseDtoList = new ArrayList<>();
        for(Card card : cards){
            cardResponseDtoList.add(CardResponseDto.builder()
                    .id(card.getId())
                    .customerName(card.getCustomer().getName())
                    .cardNo(card.getCardNo())
                    .cardType(card.getCardType())
                    .validTill(card.getValidTill())
                    .build());
        }
        return cardResponseDtoList;
    }
}

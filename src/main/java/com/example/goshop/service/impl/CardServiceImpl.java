package com.example.goshop.service.impl;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.exception.CustomerNotExistException;
import com.example.goshop.exception.InvalidCardException;
import com.example.goshop.model.Card;
import com.example.goshop.model.Customer;
import com.example.goshop.repository.CardRepository;
import com.example.goshop.repository.CustomerRepository;
import com.example.goshop.service.CardService;
import com.example.goshop.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotExistException {
        Customer customer = customerRepository.findByEmailId(cardRequestDto.getCustomerEmailId());

        if(customer == null){
            throw new CustomerNotExistException("Customer with this email Id not present");
        }

        //dto->entity
        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);

        card.setCustomer(customer);
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);

        //prepare response Dto
        return CardTransformer.cardToCardResponseDto(card);
    }

    @Override
    public Optional<Card> isCardValid(String cardNo, int cvv) {
        Date date = new Date();
        Card card = cardRepository.findByCardNo(cardNo);
        if(card == null || card.getCvv()!=cvv || date.after(card.getValidTill())){
            return Optional.empty();
        }
        return Optional.of(card);
    }

    @Override
    public String generateMaskedCardNo(Card card) {
        String cardNo = card.getCardNo();
        StringBuilder sb = new StringBuilder();
        int n = cardNo.length();

        for(int i=0; i<n-4; i++){
            sb.append("X");
        }
        sb.append(cardNo.substring(n-4));

        return sb.toString();
    }
}

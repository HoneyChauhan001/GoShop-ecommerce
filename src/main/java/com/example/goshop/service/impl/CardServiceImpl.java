package com.example.goshop.service.impl;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.exception.CustomerNotExistException;
import com.example.goshop.model.Card;
import com.example.goshop.model.Customer;
import com.example.goshop.repository.CardRepository;
import com.example.goshop.repository.CustomerRepository;
import com.example.goshop.service.CardService;
import com.example.goshop.transformer.CardTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto, String userEmailId) throws Exception {
        Customer customer = customerRepository.findByEmailId(userEmailId);

        if(customer == null){
            throw new CustomerNotExistException("Customer with this email Id not present");
        }

        log.info("Number of cards present : {}",customer.getCards().size());
        if(customer.getCards().size() >= 3){
            throw new Exception("Maximum cards limit reached");
        }

        //dto->entity
        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);

        card.setCustomer(customer);
        customer.getCards().add(card);

        Customer savedCustomer = customerRepository.save(customer);

        //prepare response Dto
        return CardTransformer.cardToCardResponseDto(customer.getCards().get(customer.getCards().size()-1));
    }

    @Override
    public List<CardResponseDto> getAllCards(String userEmailId) throws CustomerNotExistException {
        Customer customer = customerRepository.findByEmailId(userEmailId);
        if(customer == null){
            throw new CustomerNotExistException("Customer with this email Id not present");
        }

        List<Card> cards = customer.getCards();
        return CardTransformer.cardToCardResponseDto(cards);
    }

    @Override
    public CardResponseDto getById(String userEmailId, CardResponseDto cardRequestDto) throws Exception {
        Customer customer = customerRepository.findByEmailId(userEmailId);
        Optional<Card> cardOptional = cardRepository.findById(cardRequestDto.getId());
        if(cardOptional.isEmpty()){
            throw new Exception("No Card with this Id");
        }
        Card card = cardOptional.get();

        if(card.getCustomer().equals(customer)){
            return CardTransformer.cardToCardResponseDto(card);
        } else {
            throw new Exception("Card don't belong to this user");
        }

    }
}

package com.example.goshop.service.impl;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.exception.CustomerNotExistException;
import com.example.goshop.model.Card;
import com.example.goshop.model.Customer;
import com.example.goshop.repository.CustomerRepository;
import com.example.goshop.service.CardService;
import com.example.goshop.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;
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
}

package com.example.goshop.controller;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.exception.CustomerNotExistException;
import com.example.goshop.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        try{
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        } catch (CustomerNotExistException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}

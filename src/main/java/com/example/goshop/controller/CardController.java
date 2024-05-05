package com.example.goshop.controller;

import com.example.goshop.dto.requestdto.CardRequestDto;
import com.example.goshop.dto.responsedto.CardResponseDto;
import com.example.goshop.exception.CustomerNotExistException;
import com.example.goshop.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@Slf4j
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto, Authentication authentication){

        try{
            String userEmailId = authentication.getName();
            log.info("User Email Id : {}",userEmailId);
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto,userEmailId);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        } catch (CustomerNotExistException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getCards(Authentication authentication){
        try{

            String userEmailId = authentication.getName();
            log.info("User Email Id : {}",userEmailId);
            List<CardResponseDto> cardResponseDtoList = cardService.getAllCards(userEmailId);
            return new ResponseEntity(cardResponseDtoList,HttpStatus.OK);
        } catch (CustomerNotExistException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/getById")
    public ResponseEntity getCardById(@RequestBody CardResponseDto cardRequestDto,Authentication authentication){
        try{
            String userEmailId = authentication.getName();
            log.info("User Email Id : {}",userEmailId);
            CardResponseDto cardResponseDto = cardService.getById(userEmailId,cardRequestDto);
            return new ResponseEntity(cardResponseDto,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

//    @PostMapping("/removeById")
//    public ResponseEntity removeById(@RequestBody CardResponseDto cardRequestDto, Authentication authentication){
//        try{
//            String userEmailId = authentication.getName();
//            log.info("User Email Id : {}",userEmailId);
//        }
//    }


}

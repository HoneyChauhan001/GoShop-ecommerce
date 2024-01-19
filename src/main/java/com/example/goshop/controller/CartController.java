package com.example.goshop.controller;

import com.example.goshop.dto.requestdto.CheckOutRequestDto;
import com.example.goshop.dto.requestdto.ItemRequestDto;
import com.example.goshop.dto.responsedto.CartResponseDto;
import com.example.goshop.dto.responsedto.OrderResponseDto;
import com.example.goshop.model.Item;
import com.example.goshop.service.CartService;
import com.example.goshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;
    @PostMapping("/add-item-to-cart")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try{
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(item);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/check-out")
    public ResponseEntity checkOut(@RequestBody CheckOutRequestDto checkOutRequestDto){

        try{
            OrderResponseDto orderResponseDto = cartService.checkOut(checkOutRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}

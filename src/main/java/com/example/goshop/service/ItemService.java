package com.example.goshop.service;

import com.example.goshop.dto.requestdto.ItemRequestDto;
import com.example.goshop.exception.CustomerNotPresentException;
import com.example.goshop.exception.InSufficientQuantityException;
import com.example.goshop.exception.OutOfStockException;
import com.example.goshop.exception.ProductNotPresentException;
import com.example.goshop.model.Item;

public interface ItemService {
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotPresentException, CustomerNotPresentException, OutOfStockException, InSufficientQuantityException;
}

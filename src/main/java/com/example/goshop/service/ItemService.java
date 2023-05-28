package com.example.goshop.service;

import com.example.goshop.dto.requestdto.ItemRequestDto;
import com.example.goshop.exception.*;
import com.example.goshop.model.Item;

public interface ItemService {
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotPresentException, OutOfStockException, InSufficientQuantityException, CustomerNotExistException;
    public Item createItem(int quantity);
}

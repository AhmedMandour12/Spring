package com.ahmed.springdemo.service;

import com.ahmed.springdemo.model.Item;

import java.util.List;

public interface ItemService {
    Item addItem(Item item);
    void deleteItem(Long id);
    List<Item> viewItems();
    Item updateItem(Item item);
    Item showItembyId(Long id);
    Item searchItemById(Long id);

}

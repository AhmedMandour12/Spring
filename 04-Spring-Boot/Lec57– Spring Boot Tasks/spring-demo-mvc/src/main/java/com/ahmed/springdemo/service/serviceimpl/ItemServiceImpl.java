package com.ahmed.springdemo.service.serviceimpl;

import com.ahmed.springdemo.model.Item;
import com.ahmed.springdemo.repository.ItemRepo;
import com.ahmed.springdemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;

    @Autowired
    public ItemServiceImpl(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @Override
    public Item addItem(Item item) {

        return itemRepo.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepo.deleteById(id);

    }

    @Override
    public List<Item> viewItems() {
        return itemRepo.findAll();
    }

    @Override
    public Item updateItem(Item item) {

        return itemRepo.save(item);
    }

    @Override
    public Item showItembyId(Long id) {
        return itemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    @Override
    public Item searchItemById(Long id) {
        return itemRepo.findById(id)
                .orElseThrow(()->new RuntimeException("Item not found") );
    }
}

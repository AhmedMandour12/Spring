package com.ahmed.springdemo.controller;

import com.ahmed.springdemo.model.Item;
import com.ahmed.springdemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Item")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/home")
    public String getHome() {
        return "home";
    }

    @GetMapping("/add")
    public String addItemPage() {
        return "addItem";
    }

    @PostMapping("/add")
    public String addItem(Item item) {
        itemService.addItem(item);
        return "redirect:/Item/view";
    }

    @GetMapping("/view")
    public String viewItems(Model model) {
        List<Item> items = itemService.viewItems();
        model.addAttribute("items", items);
        return "viewItems";
    }

    @GetMapping("/update/{id}")
    public String getItem(@PathVariable Long id, Model model) {
        Item item = itemService.showItembyId(id);
        model.addAttribute("item", item);
        return "updateItem";
    }

    @PostMapping("/update")
    public String updateItem(Item item) {
        itemService.updateItem(item);
        return "redirect:/Item/view";
    }


    @PostMapping("delete")
    public String deleteItem(Long id) {
        itemService.deleteItem(id);

        return "redirect:/Item/view";
    }

    @GetMapping("/search")
    public String searchItemById(@RequestParam Long id, Model model) {

        Item item = itemService.searchItemById(id);

        model.addAttribute("items", List.of(item));

        return "viewItems";
    }

}




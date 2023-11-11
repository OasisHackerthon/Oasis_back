package Mirthon.Oasis_back.controller;

import Mirthon.Oasis_back.domain.Item;
import Mirthon.Oasis_back.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/list")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}
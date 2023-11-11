package Mirthon.Oasis_back.controller;

import Mirthon.Oasis_back.domain.Item;
import Mirthon.Oasis_back.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
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
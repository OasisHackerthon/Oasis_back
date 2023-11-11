package Mirthon.Oasis_back.controller;

import Mirthon.Oasis_back.domain.Item;
import Mirthon.Oasis_back.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true", allowedHeaders = "*")
public class ItemController {

    private final ItemService itemService;

    // Create
    @PostMapping("/api/createItem")
    public Item createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return  createdItem;
    }

    // Read
    @GetMapping("/api/getAllItems")
    public List<Item> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return items;
    }

    @GetMapping("/api/getItem/{itemId}")
    public Item getItemById(@PathVariable Long itemId) {
        Item item = itemService.getItemById(itemId);
        return item;
    }



    // Delete
    @DeleteMapping("/api/deleteItem/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        itemService.deleteItem(itemId);
    }
}
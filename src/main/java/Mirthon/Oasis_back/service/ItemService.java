package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.domain.Item;
import Mirthon.Oasis_back.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;


    // Create
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    // Read
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    // Update
    public Item updateItem(Long itemId, Item updatedItem) {
        Item existingItem = itemRepository.findById(itemId).get();
        if (existingItem != null) {
            Item item = existingItem;
            item.setItemName(updatedItem.getItemName());
            item.setItemPrice(updatedItem.getItemPrice());
            item.setItemImageUrl(updatedItem.getItemImageUrl());
            item.setItemBrand(updatedItem.getItemBrand());
            // You can update other fields as needed

            return itemRepository.save(item);
        } else {
            // Handle the case where the item with the given ID is not found
            return null;
        }
    }

    // Delete
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}

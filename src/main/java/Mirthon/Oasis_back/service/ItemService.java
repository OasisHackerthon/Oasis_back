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

    public Long getItemPriceByItemId(Long itemId) {
        // 아이디로 아이템을 찾습니다.
        Item item = itemRepository.findById(itemId).orElse(null);

        if (item != null) {
            // 아이템이 존재하면 해당 아이템의 가격을 반환합니다.
            return item.getItemPrice();
        } else {
            // 아이템이 없으면 원하는 방식으로 처리합니다.
            return null; // 또는 예외를 던지거나 다른 방식으로 처리할 수 있습니다.
        }
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

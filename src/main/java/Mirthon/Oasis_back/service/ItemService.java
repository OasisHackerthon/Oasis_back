package Mirthon.Oasis_back.service;

import Mirthon.Oasis_back.domain.Item;
import Mirthon.Oasis_back.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll(); // 모든 Item 가져오기
    }
}

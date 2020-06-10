package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.exception.EntityNotFoundException;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemEditor {
    private final ItemRepository itemRepository;
    private final BackpackRepository backpackRepository;

    public ItemEditor(ItemRepository itemRepository, BackpackRepository backpackRepository) {
        this.itemRepository = itemRepository;
        this.backpackRepository = backpackRepository;
    }

    public void saveItem(Long id, Item item) {

        Optional<Backpack> oBackpack = backpackRepository.findById(id);
        if (oBackpack.isEmpty()) {
            throw new EntityNotFoundException("no backpack with the id: " + id + " found");
        }
        Item itemSaved = itemRepository.save(item);
        Backpack backpack = oBackpack.get();
        backpack.getItems().add(itemSaved);
        backpackRepository.save(backpack);
    }

    public Item getItem(Long itemId) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            return oItem.get();
        } else {
            throw new EntityNotFoundException("No item found!");
        }
    }

    public Item updateItem(Long itemId, Item item) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            item.setId(itemId);
            return itemRepository.save(item);
        } else {
            throw new EntityNotFoundException("No item found!");
        }
    }

    public boolean deleteItem(Long backpackID, Long itemId) {
        if (!backpackRepository.existsById(backpackID) || !itemRepository.existsById(itemId)) {
            return false;
        }
        Backpack backpack = backpackRepository.findById(backpackID).get();
        Item item = itemRepository.findById(itemId).get();
        backpack.getItems().remove(item);
        backpackRepository.save(backpack);
        itemRepository.deleteById(itemId);
        return true;
    }

    public List<Item> getAllItems(Long backpackID) {
        Optional<Backpack> oBackpack = backpackRepository.findById(backpackID);
        List<Item> items = new ArrayList<>();
        if (oBackpack.isPresent()) {
            items = oBackpack.get().getItems();
        }
        return items;
    }
}

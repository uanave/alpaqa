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
public class BackpackService {
    private final BackpackRepository backpackRepository;
    private final ItemRepository itemRepository;

    public BackpackService(BackpackRepository backpackRepository, ItemRepository itemRepository) {
        this.backpackRepository = backpackRepository;
        this.itemRepository = itemRepository;
    }

    public List<Item> backpackItemsPreview(Long backpackID) {
        Optional<Backpack> oBackpack = backpackRepository.findById(backpackID);
        List<Item> items = new ArrayList<>();
        if (oBackpack.isPresent()) {
            items = oBackpack.get().getItems();
        }
        return items;
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

    public Item getItem(Long itemId) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            return oItem.get();
        } else {
            throw new EntityNotFoundException("No item found!");
        }
    }

    public Backpack getBackpack(Long backpackId) {
        Optional<Backpack> oBackpack = backpackRepository.findById(backpackId);
        if (oBackpack.isPresent()) {
            return oBackpack.get();
        } else {
            throw new EntityNotFoundException("No backpack found!");
        }
    }

    public Backpack updateBackpack(Long backpackId, Backpack backpack) {
        Optional<Backpack> oBackpack = backpackRepository.findById(backpackId);
        if (oBackpack.isPresent()) {
            backpack.setId(backpackId);
            return backpackRepository.save(backpack);
        } else {
            throw new EntityNotFoundException("No backpack found!");
        }
    }
    public boolean deleteItem(Long backpackID, Long itemId) {
        if (!backpackRepository.existsById(backpackID) && !itemRepository.existsById(itemId)) {
            return false;
        }
        Backpack backpack = backpackRepository.findById(backpackID).get();
        Item item = itemRepository.findById(itemId).get();
        backpack.getItems().remove(item);
        backpackRepository.save(backpack);
        itemRepository.deleteById(itemId);
        return true;
    }

}

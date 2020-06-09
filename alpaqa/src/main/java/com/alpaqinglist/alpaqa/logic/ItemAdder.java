package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.exception.EntityNotFoundException;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemAdder {
    private final BackpackRepository backpackRepository;
    private final ItemRepository itemRepository;

    public ItemAdder(BackpackRepository backpackRepository, ItemRepository itemRepository) {
        this.backpackRepository = backpackRepository;
        this.itemRepository = itemRepository;
    }

    public void saveItemInBackpack(Long id, Item item) {

        Optional<Backpack> oBackpack = backpackRepository.findById(id);
        if (oBackpack.isEmpty()) {
            throw new EntityNotFoundException("no backpack with the id: " + id + " found");
        }
        Item itemSaved = itemRepository.save(item);
        Backpack backpack = oBackpack.get();
        backpack.getItems().add(itemSaved);
        backpackRepository.save(backpack);
    }
}

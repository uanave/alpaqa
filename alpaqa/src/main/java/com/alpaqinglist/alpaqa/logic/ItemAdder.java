package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.exception.BackpackNotFoundException;
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
        if(oBackpack.isEmpty()){
            throw new BackpackNotFoundException("no backpack found while trying to add an item");
        }
        Item itemSaved = itemRepository.save(item);
        oBackpack.ifPresent(backpack -> {
            backpack.getItems().add(itemSaved);
            backpackRepository.save(backpack);
        });

//        try {
//            oBackpack.ifPresentOrElse(backpack -> {
//                        backpack.getItems().add(itemSaved);
//                        backpackRepository.save(backpack);
//                    },
//                    () ->
//                throw new Exception()
//                    );
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}

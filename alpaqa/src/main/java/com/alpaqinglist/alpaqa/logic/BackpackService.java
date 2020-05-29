package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BackpackService {
    private final BackpackRepository repository;

    public BackpackService(BackpackRepository repository) {
        this.repository = repository;
    }

    public List<Item> backpackItemsPreview(Long backpackID) {
        Optional<Backpack> oBackpack = repository.findById(backpackID);
        List<Item> items = new ArrayList<>();
        if (oBackpack.isPresent()) {
            items = oBackpack.get().getItems();
        }
        return items;
    }
}

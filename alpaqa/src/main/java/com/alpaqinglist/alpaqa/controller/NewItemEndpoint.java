package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backpack")
public class NewItemEndpoint {
    private final ItemAdder itemAdder;

    public NewItemEndpoint(ItemAdder itemAdder) {
        this.itemAdder = itemAdder;
    }

    @PutMapping("/{backpackId}/add-new-item")
    void addItem(@PathVariable Long backpackId, Item item){
        itemAdder.saveItemInBackpack(backpackId, item);
    }
}

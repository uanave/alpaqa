package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackService;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backpacks")
public class BackpackContentEndpoint {
    private final BackpackService service;
    private final ItemRepository itemRepository;
    private final BackpackRepository backpackRepository;

    public BackpackContentEndpoint(BackpackService service, ItemRepository itemRepository, BackpackRepository backpackRepository) {
        this.service = service;
        this.itemRepository = itemRepository;
        this.backpackRepository = backpackRepository;
    }

    @GetMapping("{id}/items")
    List<Item> getItems(@PathVariable Long id) {
        return service.backpackItemsPreview(id);
    }

    @GetMapping("{id}/items/{itemId}")
    Item getItem(@PathVariable Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    @PutMapping("/{backpackId}/items/{itemId}")
    Backpack updateBackpack(@PathVariable Long backpackId, @RequestBody Item item) {
        itemRepository.save(item);
        return backpackRepository.findById(backpackId).orElse(null);
    }
}

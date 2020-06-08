package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.exception.EntityNotFoundException;
import com.alpaqinglist.alpaqa.logic.BackpackService;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/backpacks")
public class BackpackContentEndpoint {
    private final BackpackService service;
    private final ItemRepository itemRepository;

    public BackpackContentEndpoint(BackpackService service, ItemRepository itemRepository) {
        this.service = service;
        this.itemRepository = itemRepository;
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
    Item updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        Optional<Item> oItem = itemRepository.findById(itemId);
        if (oItem.isPresent()) {
            item.setId(itemId);
            itemRepository.save(item);
            return item;
        } else {
            throw new EntityNotFoundException("No item found");
        }
    }
}

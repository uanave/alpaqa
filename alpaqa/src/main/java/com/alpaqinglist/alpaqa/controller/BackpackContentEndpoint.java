package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackService;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backpacks")
public class BackpackContentEndpoint {
    private final BackpackService service;

    public BackpackContentEndpoint(BackpackService service) {
        this.service = service;
    }

    @GetMapping("/{id}/items")
    List<Item> getItems(@PathVariable Long id) {
        return service.backpackItemsPreview(id);
    }

    @GetMapping("/{id}/items/{itemId}")
    Item getItem(@PathVariable Long itemId) {
        return service.getItem(itemId);
    }

    @PutMapping("/{backpackId}/items/{itemId}")
    Item updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        return service.updateItem(itemId, item);
    }

    @GetMapping("/{backpackId}")
    Backpack getBackpack(@PathVariable Long backpackId) {
        return service.getBackpack(backpackId);
    }

    @PutMapping("/{backpackId}")
    Backpack updateBackpack(@PathVariable Long backpackId, @RequestBody Backpack backpack) {
        return service.updateBackpack(backpackId, backpack);
    }
}

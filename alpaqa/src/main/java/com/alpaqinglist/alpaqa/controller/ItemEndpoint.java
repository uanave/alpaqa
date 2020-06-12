package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.ItemEditor;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/backpacks")
public class ItemEndpoint {
    private final ItemEditor itemEditor;

    public ItemEndpoint(ItemEditor itemEditor) {
        this.itemEditor = itemEditor;
    }

    @PutMapping("/{backpackId}/add-new-item")
    void addItem(@PathVariable Long backpackId, @Valid @RequestBody Item item) {
        itemEditor.saveItem(backpackId, item);
    }

    @GetMapping("/{backpackId}/items/{itemId}")
    Item getItem(@PathVariable Long itemId) {
        return itemEditor.getItem(itemId);
    }

    @PutMapping("/{backpackId}/items/{itemId}")
    Item updateItem(@PathVariable Long itemId, @RequestBody Item item) {
        return itemEditor.updateItem(itemId, item);
    }

    @GetMapping("/{backpackId}/items")
    List<Item> getItems(@PathVariable Long backpackId) {
        return itemEditor.getAllItems(backpackId);
    }

    @DeleteMapping("/{backpackId}/items/{itemId}")
    void deleteItem(@PathVariable Long backpackId, @PathVariable Long itemId) {
        itemEditor.deleteItem(backpackId, itemId);
    }
}

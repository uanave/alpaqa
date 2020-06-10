package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackpackService {
    private final BackpackEditor backpackEditor;
    private final ItemEditor itemEditor;

    public BackpackService(BackpackEditor backpackEditor, ItemEditor itemEditor) {
        this.backpackEditor = backpackEditor;
        this.itemEditor = itemEditor;
    }

    public Item getItem(Long itemId) {
        return itemEditor.getItem(itemId);
    }

    public Item updateItem(Long itemId, Item item) {
        return itemEditor.updateItem(itemId, item);
    }

    public boolean deleteItem(Long backpackId, Long itemId) {
        return itemEditor.deleteItem(backpackId, itemId);
    }

    public List<Item> getAllItems(Long backpackId) {
        return itemEditor.getAllItems(backpackId);
    }

    public Backpack getBackpack(Long backpackId) {
        return backpackEditor.getBackpack(backpackId);
    }

    public Backpack updateBackpack(Long backpackId, Backpack backpack) {
        return backpackEditor.updateBackpack(backpackId, backpack);
    }

    public void deleteBackpack(Long backpackId) {
        backpackEditor.delete(backpackId);
    }

    public List<Backpack> getAllBackpacks() {
        return backpackEditor.getAll();
    }
}

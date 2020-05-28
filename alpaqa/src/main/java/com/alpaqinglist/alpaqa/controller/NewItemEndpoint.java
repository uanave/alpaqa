package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.exception.BackpackNotFoundException;
import com.alpaqinglist.alpaqa.logic.ItemAdder;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backpack")
public class NewItemEndpoint {
    private final ItemAdder itemAdder;

    public NewItemEndpoint(ItemAdder itemAdder) {
        this.itemAdder = itemAdder;
    }

    @PutMapping("/{backpackId}/add-new-item")
    void addItem(@PathVariable Long backpackId, Item item) {
        itemAdder.saveItemInBackpack(backpackId, item);
    }

    @ExceptionHandler({BackpackNotFoundException.class})
    public ResponseEntity<String> notFound(BackpackNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackDeleter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backpacks")
public class BackpackDeletionEndpoint {

    private BackpackDeleter backpackDeleter;

    public BackpackDeletionEndpoint(BackpackDeleter backpackDeleter) {
        this.backpackDeleter = backpackDeleter;
    }

    @DeleteMapping("/{id}")
    void deleteBackpack(@PathVariable Long id) {
        backpackDeleter.delete(id);
    }
}

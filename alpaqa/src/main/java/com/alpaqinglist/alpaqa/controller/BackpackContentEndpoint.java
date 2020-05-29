package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackService;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/backpack")
public class BackpackContentEndpoint {
    private final BackpackService service;

    public BackpackContentEndpoint(BackpackService service) {
        this.service = service;
    }

    @GetMapping("{id}/items")
    List<Item> getItems(@PathVariable Long id) {
        return service.backpackItemsPreview(id);
    }
}

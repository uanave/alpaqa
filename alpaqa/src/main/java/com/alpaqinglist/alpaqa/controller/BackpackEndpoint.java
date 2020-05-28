package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.logic.BackpackCreator;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backpack")
public class BackpackEndpoint {
    private final BackpackCreator backpackCreator;

    public BackpackEndpoint(BackpackCreator backpackCreator) {
        this.backpackCreator = backpackCreator;
    }

    @PostMapping
    BackpackDTO createNewBackpack(@RequestBody BackpackDTO backpackDTO) {

        return backpackCreator.create(backpackDTO).orElseGet(null);

    }

    @GetMapping
    List<Backpack> getAllBackpacks() {

        return backpackCreator.getAll();
    }
}

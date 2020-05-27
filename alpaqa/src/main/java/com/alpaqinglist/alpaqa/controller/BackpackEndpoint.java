package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.logic.BackpackCreator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/backpack")
public class BackpackEndpoint {
    private final BackpackCreator backpackCreator;

    public BackpackEndpoint(BackpackCreator backpackCreator) {
        this.backpackCreator = backpackCreator;
    }
    @PostMapping
    BackpackDTO createNewBackpack(@RequestBody BackpackDTO backpackDTO){

        return backpackCreator.create(backpackDTO);

    }
}

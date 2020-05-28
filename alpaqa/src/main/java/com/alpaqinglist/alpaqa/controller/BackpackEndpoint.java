package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.logic.BackpackCreator;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/backpack")
public class BackpackEndpoint {
    private final BackpackCreator backpackCreator;

    public BackpackEndpoint(BackpackCreator backpackCreator) {
        this.backpackCreator = backpackCreator;
    }

    @PostMapping
    ResponseEntity<BackpackDTO> createNewBackpack(@RequestBody BackpackDTO backpackDTO) {

        Optional<BackpackDTO> oBackpack = backpackCreator.create(backpackDTO);
        return oBackpack.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

    }

    @GetMapping
    List<Backpack> getAllBackpacks() {

        return backpackCreator.getAll();
    }
}

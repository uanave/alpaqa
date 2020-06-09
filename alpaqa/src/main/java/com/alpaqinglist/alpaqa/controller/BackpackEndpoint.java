package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.data.error.ApiError;
import com.alpaqinglist.alpaqa.logic.BackpackCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/backpacks")
public class BackpackEndpoint {
    private final BackpackCreator backpackCreator;

    public BackpackEndpoint(BackpackCreator backpackCreator) {
        this.backpackCreator = backpackCreator;
    }

    @PostMapping
    ResponseEntity<Object> createNewBackpack(@Valid @RequestBody BackpackDTO backpackDTO) {

        Optional<BackpackDTO> oBackpack = backpackCreator.create(backpackDTO);
        return oBackpack.map(dto -> new ResponseEntity<>((Object) dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Error while creating backpack"), HttpStatus.BAD_REQUEST));
    }

    @GetMapping
    List<BackpackDTO> getAllBackpacks() {
        return backpackCreator.getAll();
    }

}

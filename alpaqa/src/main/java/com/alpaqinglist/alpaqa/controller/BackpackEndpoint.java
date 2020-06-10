package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.data.error.ApiError;
import com.alpaqinglist.alpaqa.logic.BackpackEditor;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/backpacks")
public class BackpackEndpoint {
    private final BackpackEditor backpackEditor;

    public BackpackEndpoint(BackpackEditor backpackEditor) {
        this.backpackEditor = backpackEditor;
    }

    @PostMapping
    ResponseEntity<Object> addBackpack(@Valid @RequestBody Backpack backpack) {
        Optional<Backpack> oBackpack = backpackEditor.create(backpack);
        return oBackpack.map(dto -> new ResponseEntity<>((Object) dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Error while creating backpack"), HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{backpackId}")
    Backpack getBackpack(@PathVariable Long backpackId) {
        return backpackEditor.getBackpack(backpackId);
    }

    @PutMapping("/{backpackId}")
    Backpack updateBackpack(@PathVariable Long backpackId, @RequestBody Backpack backpack) {
        return backpackEditor.updateBackpack(backpackId, backpack);
    }

    @DeleteMapping("/{backpackId}")
    void deleteBackpack(@PathVariable Long backpackId) {
        backpackEditor.delete(backpackId);
    }

    @GetMapping
    List<Backpack> getAllBackpacks() {
        return backpackEditor.getAll();
    }
}

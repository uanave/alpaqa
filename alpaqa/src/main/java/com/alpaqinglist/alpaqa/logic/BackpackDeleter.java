package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BackpackDeleter {

    BackpackRepository backpackRepository;

    public BackpackDeleter(BackpackRepository backpackRepository) {
        this.backpackRepository = backpackRepository;
    }

    public void delete(Long id) {
        Optional<Backpack> oBackpack = backpackRepository.findById(id);
        oBackpack.ifPresent(e -> {
            backpackRepository.delete(e);
        });
    }
}

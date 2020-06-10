package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.exception.EntityNotFoundException;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BackpackEditor {
    private final BackpackRepository backpackRepository;

    public BackpackEditor(BackpackRepository backpackRepository) {
        this.backpackRepository = backpackRepository;
    }

    public Optional<Backpack> create(Backpack backpack) {
        if (backpackRepository.existsByName(backpack.getName())) {
            return Optional.empty();
        }
        Backpack backpackSaved = backpackRepository.save(backpack);
        return Optional.of(backpackSaved);
    }

    public Backpack getBackpack(Long backpackId) {
        Optional<Backpack> oBackpack = backpackRepository.findById(backpackId);
        if (oBackpack.isPresent()) {
            return oBackpack.get();
        } else {
            throw new EntityNotFoundException("No backpack found!");
        }
    }

    public Backpack updateBackpack(Long backpackId, Backpack backpack) {
        Optional<Backpack> oBackpack = backpackRepository.findById(backpackId);
        if (oBackpack.isPresent()) {
            oBackpack.get().setName(backpack.getName());
            oBackpack.get().setDescription(backpack.getDescription());
            return backpackRepository.save(oBackpack.get());
        } else {
            throw new EntityNotFoundException("No backpack found!");
        }
    }

    public void delete(Long id) {
        Optional<Backpack> oBackpack = backpackRepository.findById(id);
        oBackpack.ifPresent(backpackRepository::delete);
    }

    public List<Backpack> getAll() {
        return backpackRepository.findAllByOrderByIdDesc();
    }
}

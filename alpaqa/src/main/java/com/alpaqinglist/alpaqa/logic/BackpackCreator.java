package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import org.springframework.stereotype.Service;

@Service
public class BackpackCreator {
    private final BackpackTranslater translator;
    private final BackpackRepository repository;

    public BackpackCreator(BackpackTranslater translator, BackpackRepository repository) {
        this.translator = translator;
        this.repository = repository;
    }

    public BackpackDTO create(BackpackDTO backpackDTO) {
        Backpack backpack = translator.translateFromDTO(backpackDTO);
        Backpack backpackSaved = repository.save(backpackDTO);
        return translator.translateToDTO(backpackSaved);
    }
}

package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import org.springframework.stereotype.Service;

@Service
public class BackpackCreator {
    private final BackpackTranslator translator;
    private final BackpackRepository repository;

    public BackpackCreator(BackpackTranslator translator, BackpackRepository repository) {
        this.translator = translator;
        this.repository = repository;
    }

    public BackpackDTO create(BackpackDTO backpackDTO) {
        Backpack backpack = translator.translateToBackpack(backpackDTO);
        Backpack backpackSaved = repository.save(backpack);
        return translator.translateToBackpackTDO(backpackSaved);
    }
}

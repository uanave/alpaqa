package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BackpackCreator {
    private final BackpackTranslator translator;
    private final BackpackRepository repository;

    public BackpackCreator(BackpackTranslator translator, BackpackRepository repository) {
        this.translator = translator;
        this.repository = repository;
    }

    public Optional<BackpackDTO> create(BackpackDTO backpackDTO) {
        if(repository.existsByName(backpackDTO.getName())) {
        return Optional.empty();
        }
        Backpack backpack = translator.translateToBackpack(backpackDTO);
        Backpack backpackSaved = repository.save(backpack);
        BackpackDTO savedBackpackDTO = translator.translateToBackpackTDO(backpackSaved);
        return Optional.of(savedBackpackDTO);
    }
    public List<BackpackDTO> getAll(){

        return repository.findAll().stream()
                .map(translator::translateToBackpackTDO)
                .collect(Collectors.toList());
    }
}

package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BackpackImporter {
    private final BackpackRepository backpackRepository;
    private final ItemRepository itemRepository;
    private final BackpackExtractor backpackExtractor;
    private int backpackNameSequence;
    private int sequenceStep;

    public BackpackImporter(BackpackRepository backpackRepository, ItemRepository itemRepository, BackpackExtractor backpackExtractor,
                            @Value("${backpack.details.backpackNameSequence}") int backpackNameSequence,
                            @Value("${backpack.details.sequenceStep}") int sequenceStep) {
        this.backpackRepository = backpackRepository;
        this.itemRepository = itemRepository;
        this.backpackExtractor = backpackExtractor;
        this.backpackNameSequence = backpackNameSequence;
        this.sequenceStep = sequenceStep;
    }

    public Optional<Backpack> backpackSavior(MultipartFile file) {
        Optional<Backpack> oBackpack = backpackExtractor.extractFromMultipartFile(file);
        if (oBackpack.isEmpty()) {
            return oBackpack;
        }
        Backpack backpack = oBackpack.get();
        backpack.setId(null);
        do {
            String backpackName = backpack.getName();
            if (!backpackRepository.existsByName(backpackName)) {
                return preparingAndSaving(backpack);
            }
            String nameWithNextNumber = nameGenerator(backpackName);
            backpack.setName(nameWithNextNumber);
        } while (true);
    }


    Optional<Backpack> preparingAndSaving(Backpack backpack) {
        List<Item> items = backpack.getItems();
        items.forEach(item -> item.setId(null));
        items = itemRepository.saveAll(items);
        backpack.setItems(items);
        return Optional.of(backpackRepository.save(backpack));
    }

    String nameGenerator(String backpackName) {
        String sequenceFormatter = "("+backpackNameSequence+")";
        backpackName = backpackName + sequenceFormatter;
        backpackNameSequence += sequenceStep;
        return backpackName;
    }
}

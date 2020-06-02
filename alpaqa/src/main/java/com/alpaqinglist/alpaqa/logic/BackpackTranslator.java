package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.springframework.stereotype.Service;

@Service
public class BackpackTranslator {

    public Backpack translateToBackpack(BackpackDTO backpackDTO) {
        return new Backpack(
                backpackDTO.getId(),
                backpackDTO.getName(),
                backpackDTO.getDescription(),
                backpackDTO.getTotalWeight(),
                backpackDTO.getCategory(),
                backpackDTO.getImagePath(),
                backpackDTO.getItems()
                );

    }

    public BackpackDTO translateToBackpackTDO(Backpack backpack) {
        return new BackpackDTO(
                backpack.getId(),
                backpack.getName(),
                backpack.getDescription(),
                backpack.getCategory(),
                backpack.getItems(),
                backpack.getImage(),
                backpack.getWeight()
        );
    }
}

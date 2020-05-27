package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackpackTranslatorTest {
    BackpackTranslator translator = new BackpackTranslator();

    @Test
    void translateToBackpack() {
        BackpackDTO backpackDTO = new BackpackDTO("test", "testDescription");
        Backpack expected = new Backpack("test", "testDescription");
        Backpack result = translator.translateToBackpack(backpackDTO);
        assertEquals(expected, result);
    }

    @Test
    void translateToBackpackTDO() {
        BackpackDTO expected = new BackpackDTO(1l, "test", "testDescription");
        Backpack backpack = new Backpack(1l, "test", "testDescription");
        BackpackDTO result = translator.translateToBackpackTDO(backpack);
        assertEquals(expected, result);
    }
}
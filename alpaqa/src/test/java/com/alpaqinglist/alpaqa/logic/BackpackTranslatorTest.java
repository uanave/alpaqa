package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BackpackTranslatorTest {
    BackpackTranslator translator = new BackpackTranslator();

    @Test
    void translateToBackpack() {
        BackpackDTO backpackDTO = new BackpackDTO(1L, "test", "testDescription", "TestCat", List.of(),"TestImage", 1, 1L);
        Backpack expected = new Backpack(1L, "test", "testDescription", 1, 1, "TestCat", "TestImage", List.of());
        Backpack result = translator.translateToBackpack(backpackDTO);
        assertEquals(expected, result);
    }

    @Test
    void translateToBackpackTDO() {
        BackpackDTO expected = new BackpackDTO(1L, "test", "testDescription", List.of());
        Backpack backpack = new Backpack(1L, "test", "testDescription");
        BackpackDTO result = translator.translateToBackpackTDO(backpack);
        assertEquals(expected, result);
    }
}
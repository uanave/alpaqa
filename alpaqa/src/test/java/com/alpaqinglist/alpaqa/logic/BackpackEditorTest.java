package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.exception.EntityNotFoundException;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class BackpackEditorTest {

    @Autowired
    BackpackEditor backpackEditor;

    @MockBean
    BackpackRepository backpackRepository;

    Backpack backpack = new Backpack("testName", "testDescription");
    Backpack backpackInput = new Backpack();

    Long backpackID = 1L;

    @Test
    void createWithNonExistingBackpackName() {
        backpackInput.setName(backpack.getName());
        backpackInput.setDescription(backpack.getDescription());
        when(backpackRepository.existsByName(backpackInput.getName()))
                .thenReturn(false);
        when(backpackRepository.save(backpack))
                .thenReturn(backpack);

        Optional<Backpack> oResult = backpackEditor.create(backpackInput);
        verify(backpackRepository).existsByName(backpackInput.getName());
        verify(backpackRepository).save(backpack);
        assertEquals(backpackInput, oResult.get());
    }

    @Test
    void createWithExistingBackpackName() {
        backpackInput.setName(backpack.getName());
        backpackInput.setDescription(backpack.getDescription());
        when(backpackRepository.existsByName(backpackInput.getName()))
                .thenReturn(true);
        Optional<Backpack> oBackpack = backpackEditor.create(this.backpackInput);
        verify(backpackRepository).existsByName(this.backpackInput.getName());
        assertTrue(oBackpack.isEmpty());
    }

    @Test
    void getExistingBackpack() {
        when(backpackRepository.findById(backpackID))
                .thenReturn(Optional.of(backpack));
        backpackEditor.getBackpack(backpackID);
        verify(backpackRepository).findById(backpackID);
    }

    @Test
    void getNonExistingBackpack() {
        when(backpackRepository.findById(backpackID))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            backpackEditor.getBackpack(backpackID);
        });
        verify(backpackRepository).findById(backpackID);
    }

    @Test
    void updateExistingBackpack() {
        backpack.setId(backpackID);
        Backpack expected = new Backpack(backpackID, "UpdatedName", "UpdatedDescription");
        Backpack input = new Backpack(null, "UpdatedName", "UpdatedDescription");
        when(backpackRepository.findById(backpackID))
                .thenReturn(Optional.of(backpack));
        when(backpackRepository.save(backpack))
                .thenReturn(backpack);
        assertNotEquals(expected, backpack);
        Backpack result = backpackEditor.updateBackpack(backpackID, input);
        verify(backpackRepository).findById(backpackID);
        verify(backpackRepository).save(backpack);
        assertEquals(expected, result);
    }

    @Test
    void updateNonExistingBackpack() {
        when(backpackRepository.findById(backpackID))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            backpackEditor.updateBackpack(backpackID, backpack);
        });
    }

    @Test
    void delete() {
        when(backpackRepository.findById(backpackID)).thenReturn(Optional.of(new Backpack()));

        backpackEditor.delete(backpackID);

        verify(backpackRepository).delete(any());
    }

    @Test
    void getAll() {
        backpackEditor.getAll();
        verify(backpackRepository).findAllByOrderByIdDesc();
    }
}
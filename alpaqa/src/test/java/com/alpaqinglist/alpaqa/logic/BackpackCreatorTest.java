package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import com.alpaqinglist.alpaqa.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class BackpackCreatorTest {
    @Autowired
    BackpackCreator creator;

    @MockBean
    BackpackTranslator translator;
    @MockBean
    BackpackRepository repository;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    UserRepository userRepository;


    Backpack backpack = new Backpack("testName", "testDescription");
    BackpackDTO backpackDTO = new BackpackDTO();


    @Test
    void createWithNonExistingBackpackName() {
        backpackDTO.setName(backpack.getName());
        backpackDTO.setDescription(backpack.getDescription());
        when(repository.existsByName(backpackDTO.getName()))
                .thenReturn(false);
        when(translator.translateToBackpack(backpackDTO))
                .thenReturn(backpack);
        when(repository.save(backpack))
                .thenReturn(backpack);
        when(translator.translateToBackpackTDO(backpack))
                .thenReturn(backpackDTO);

        Optional<BackpackDTO> oResult = creator.create(backpackDTO);
        verify(repository).existsByName(backpackDTO.getName());
        verify(translator).translateToBackpack(this.backpackDTO);
        verify(repository).save(backpack);
        verify(translator).translateToBackpackTDO(backpack);
        assertEquals(backpackDTO, oResult.get());

    }
    @Test
    void createWithExistingBackpackName() {
        backpackDTO.setName(backpack.getName());
        backpackDTO.setDescription(backpack.getDescription());
        when(repository.existsByName(backpackDTO.getName()))
                .thenReturn(true);
        Optional<BackpackDTO> oBackpackDTO = creator.create(this.backpackDTO);
        verify(repository).existsByName(this.backpackDTO.getName());
        assertTrue(oBackpackDTO.isEmpty());
    }
}
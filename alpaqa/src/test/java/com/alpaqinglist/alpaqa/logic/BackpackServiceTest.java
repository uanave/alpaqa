package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class BackpackServiceTest {
    @Autowired
    BackpackService service;
    @MockBean
    BackpackRepository repository;
    Long backpackID = 1L;


    @Test
    void backpackItemsPreviewIfExists() {
        Item item = new Item("test", 1L);
        List<Item> expected = List.of(item);
        Backpack backpack = new Backpack(backpackID, "test", 1, expected);

        Mockito.when(repository.findById(backpackID))
                .thenReturn(Optional.of(backpack));
        List<Item> result = service.backpackItemsPreview(backpackID);
        verify(repository).findById(backpackID);
        assertEquals(expected, result);
    }

    @Test
    void backpackItemsPreviewNonExistingBackpack() {
        List<Item> expected = new ArrayList<>();

        Mockito.when(repository.findById(backpackID))
                .thenReturn(Optional.empty());
        List<Item> result = service.backpackItemsPreview(backpackID);
        verify(repository).findById(backpackID);
        assertEquals(expected, result);
    }
}
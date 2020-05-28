package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class ItemAdderTest {

    @Autowired
    ItemAdder itemAdder;

    @MockBean
    BackpackRepository backpackRepository;

    @MockBean
    ItemRepository itemRepository;

    @Test
    void saveItemInBackpack() {
        Item item = new Item("item");
        Backpack backpack = new Backpack(1L, "hike", 0, new ArrayList<>());
        Backpack backpackSaved = new Backpack(1L, "hike", 0, List.of(item));

        when(backpackRepository.findById(backpack.getId())).thenReturn(Optional.of(backpack));
        when(itemRepository.save(item)).thenReturn(item);
        when(backpackRepository.save(backpack)).thenReturn(backpackSaved);
        itemAdder.saveItemInBackpack(backpack.getId(), item);
        verify(backpackRepository).findById(backpack.getId());
        verify(itemRepository).save(item);
        verify(backpackRepository).save(backpackSaved);
    }
}
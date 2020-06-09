package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.exception.EntityNotFoundException;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import com.alpaqinglist.alpaqa.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class BackpackServiceTest {
    @Autowired
    BackpackService service;
    @MockBean
    BackpackRepository backpackRepository;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    UserRepository userRepository;

    Long backpackID = 1L;
    Backpack backpack = new Backpack();
    Long itemId = 1L;
    Item item = new Item();

    @Test
    void backpackItemsPreviewIfExists() {
        Item item = new Item("test", 1L);
        List<Item> expected = List.of(item);
        Backpack backpack = new Backpack(backpackID, "test", 1, expected);

        when(backpackRepository.findById(backpackID))
                .thenReturn(Optional.of(backpack));
        List<Item> result = service.backpackItemsPreview(backpackID);
        verify(backpackRepository).findById(backpackID);
        assertEquals(expected, result);
    }

    @Test
    void backpackItemsPreviewNonExistingBackpack() {
        List<Item> expected = new ArrayList<>();

        when(backpackRepository.findById(backpackID))
                .thenReturn(Optional.empty());
        List<Item> result = service.backpackItemsPreview(backpackID);
        verify(backpackRepository).findById(backpackID);
        assertEquals(expected, result);
    }

    @Test
    void updateItem() {
        Long itemId = 1L;
        Item item = new Item();
        Item expected = item;
        expected.setId(itemId);
        when(itemRepository.findById(itemId))
                .thenReturn(Optional.of(item));
        when(itemRepository.save(item))
                .thenReturn(item);
        service.updateItem(itemId, item);
        verify(itemRepository).findById(itemId);
        verify(itemRepository).save(item);
    }
    @Test
    void getItem(){
        when(itemRepository.findById(itemId))
                .thenReturn(Optional.of(item));
        Item result = service.getItem(itemId);
        verify(itemRepository).findById(itemId);
        assertEquals(item, result);
    }
    @Test
    void getNonExistingItem(){
        when(itemRepository.findById(itemId))
                .thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()->{
            service.getItem(itemId);
        });
        verify(itemRepository).findById(itemId);
    }

    @Test
    void deleteItemIfExists() {
        Item itemWithId = item;
        itemWithId.setId(itemId);
        List<Item> items = new ArrayList<>(List.of(itemWithId));
        backpack.setId(backpackID);
        backpack.setItems(items);
        when(backpackRepository.existsById(backpackID))
                .thenReturn(true);
        when(itemRepository.existsById(itemId))
                .thenReturn(true);
        when(backpackRepository.findById(backpackID))
                .thenReturn(Optional.of(backpack));
        when(itemRepository.findById(itemId))
                .thenReturn(Optional.of(item));

        boolean hasItemsList = backpack.getItems().contains(item);
        assertTrue(hasItemsList);
        boolean result = service.deleteItem(backpackID, itemId);
        hasItemsList = backpack.getItems().contains(item);

        verify(backpackRepository).existsById(backpackID);
        verify(itemRepository).existsById(itemId);
        verify(backpackRepository).findById(itemId);
        verify(itemRepository).findById(itemId);
        verify(backpackRepository).save(backpack);
        verify(itemRepository).deleteById(itemId);
        assertTrue(result);
        assertFalse(hasItemsList);
    }

    @ParameterizedTest
    @CsvSource({
            "false, false",
            "true, false",
            "false, true"
    })
    void deleteItemWithNonExistingItemOrBackpack(boolean isBackpackExists, boolean isItemExists) {
        when(backpackRepository.existsById(backpackID))
                .thenReturn(isBackpackExists);
        when(itemRepository.existsById(itemId))
                .thenReturn(isItemExists);
        boolean result = service.deleteItem(backpackID, itemId);
        assertFalse(result);
    }
}
package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.exception.EntityNotFoundException;
import com.alpaqinglist.alpaqa.logic.BackpackService;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import com.alpaqinglist.alpaqa.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BackpackContentEndpointTest {
    @Autowired
    TestRestTemplate restTemplate;
    @MockBean
    BackpackService service;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    BackpackRepository backPackRepository;
    @MockBean
    UserRepository userRepository;

    Long backpackID = 1L;
    String url = "/backpacks/" + backpackID + "/items";
    Long itemId = 1L;
    String itemUrl = url + "/" + itemId;


    Item item = new Item();

    @Test
    void getItems() {
        restTemplate.getForObject(url, Item[].class);
        verify(service).backpackItemsPreview(backpackID);
    }

    @Test
    void getExistingItem() {
        Item expected = item;
        expected.setId(itemId);
        when(itemRepository.findById(itemId))
                .thenReturn(Optional.of(item));
        when(itemRepository.save(item))
                .thenReturn(item);
        restTemplate.getForObject(itemUrl, Item.class);
        assertEquals(expected, item);
        verify(itemRepository).findById(itemId);
        verify(itemRepository).save(item);
    }

    @Test
    void getNonExistingItem() {
        when(itemRepository.findById(itemId))
                .thenReturn(Optional.empty());
        restTemplate.getForObject(itemUrl, null);
        verify(itemRepository).findById(itemId);
    }

    @Test
    void updateItemIfPresent() {
        when(itemRepository.findById(itemId))
                .thenReturn((Optional.of(item)));
        restTemplate.put(itemUrl, item ,Item.class);
        verify(itemRepository).findById(itemId);
    }
    @Test
    void updateItemIfNotPresent() {
        when(itemRepository.findById(itemId))
                .thenReturn(Optional.empty());
        restTemplate.put(itemUrl, item ,void.class);
        verify(itemRepository).findById(itemId);
    }

}
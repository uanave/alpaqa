package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackService;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import com.alpaqinglist.alpaqa.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

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
    String backpackUrl = "/backpacks/" + backpackID;


    Item item = new Item();
    Backpack backpack = new Backpack();

    @Test
    void getItems() {
        restTemplate.getForObject(url, Item[].class);
        verify(service).backpackItemsPreview(backpackID);
    }

    @Test
    void getItem() {
        when(service.getItem(itemId))
                .thenReturn(item);
        restTemplate.getForObject(itemUrl, Item.class);
        verify(service).getItem(itemId);
    }

    @Test
    void updateItem() {
        when(service.updateItem(itemId, item))
                .thenReturn(item);
        restTemplate.put(itemUrl, item, Item.class);
        verify(service).updateItem(itemId, item);
    }

    @Test
    void getBackpack() {
        when(service.getBackpack(backpackID))
                .thenReturn(backpack);
        restTemplate.getForObject(backpackUrl, Backpack.class);
        verify(service).getBackpack(backpackID);
    }

    @Test
    void updateBackpack() {
        when(service.updateBackpack(backpackID, backpack))
                .thenReturn(backpack);
        restTemplate.put(backpackUrl, backpack, Backpack.class);
        verify(service).updateBackpack(backpackID, backpack);
    }
}
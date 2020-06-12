package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.ItemEditor;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import com.alpaqinglist.alpaqa.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ItemEndpointTest {
    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    ItemEditor itemEditor;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    BackpackRepository backpackRepository;
    @MockBean
    UserRepository userRepository;

    Long backpackId = 1L;

    Long backpackID = 1L;
    String url = "/backpacks/" + backpackID + "/items";
    Long itemId = 1L;
    String itemUrl = url + "/" + itemId;
    Item item = new Item();


    @Test
    void addItem() {
        Item item = new Item("test", 1L, 1, "testD", 1, "testP", "testIm");
        String url = "/backpacks/" + backpackId + "/add-new-item";

        testRestTemplate.put(url, item);
        verify(itemEditor).saveItem(any(), any());
    }

    @Test
    void addItemVerificationEmptyName() {
        Item item = new Item("", 1L, 1, "testD", 1, "testP", "testIm");

        testRestTemplate.put(url, item);
        verifyNoInteractions(itemEditor);
    }

    @Test
    void addItemVerificationEmptyWeight() {
        Item item = new Item("test", 0L, 1, "testD", 1, "testP", "testIm");

        testRestTemplate.put(url, item);
        verifyNoInteractions(itemEditor);
    }

    void getItems() {
        testRestTemplate.getForObject(url, Item[].class);
        verify(itemEditor).getAllItems(any());
    }

    @Test
    void getItem() {
        when(itemEditor.getItem(itemId))
                .thenReturn(item);
        testRestTemplate.getForObject(itemUrl, Item.class);
        verify(itemEditor).getItem(itemId);
    }

    @Test
    void updateItem() {
        when(itemEditor.updateItem(itemId, item))
                .thenReturn(item);
        testRestTemplate.put(itemUrl, item, Item.class);
        verify(itemEditor).updateItem(itemId, item);
    }

    @Test
    void deleteItem() {

        testRestTemplate.delete(itemUrl, void.class);
        verify(itemEditor).deleteItem(backpackID, itemId);
    }
}
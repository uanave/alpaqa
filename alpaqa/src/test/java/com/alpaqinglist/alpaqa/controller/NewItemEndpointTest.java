package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.ItemAdder;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NewItemEndpointTest {
    @Autowired
    TestRestTemplate restTemplate;
    @MockBean
    ItemAdder itemAdder;

    Long backpackId = 1L;
    String url = "/backpacks/" + backpackId + "/add-new-item";


    @Test
    void addItem() {
        Item item = new Item("test", 1L, 1, "testD", 1, "testP", "testIm");

        restTemplate.put(url, item);
        verify(itemAdder).saveItemInBackpack(backpackId, item);
    }
    @Test
    void addItemVerificationEmptyName() {
        Item item = new Item("", 1L, 1, "testD", 1, "testP", "testIm");

        restTemplate.put(url, item);
        verifyNoInteractions(itemAdder);
    }
    @Test
    void addItemVerificationEmptyWeight() {
        Item item = new Item("test", 0L, 1, "testD", 1, "testP", "testIm");

        restTemplate.put(url, item);
        verifyNoInteractions(itemAdder);
    }
}
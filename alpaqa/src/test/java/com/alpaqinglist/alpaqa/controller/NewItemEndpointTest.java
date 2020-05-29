package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.ItemAdder;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class NewItemEndpointTest {
    @Autowired
    TestRestTemplate restTemplate;
    @MockBean
    ItemAdder itemAdder;

    Long backpackId = 1L;
    String url = "/backpacks/" + backpackId + "/add-new-item";

    Item item = new Item("test", 0L, 0, "testD", 1, "testP", "testIm");

    @Test
    void addItem() {
        restTemplate.put(url, item);
        Mockito.verify(itemAdder).saveItemInBackpack(backpackId, item);
    }
}
package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackService;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BackpackContentEndpointTest {
    @Autowired
    TestRestTemplate restTemplate;
    @MockBean
    BackpackService service;

    Long backpackID = 1L;
    String url = "/backpacks/" + backpackID + "/items";

    @Test
    void getItems() {
        restTemplate.getForObject(url, Item[].class);
        Mockito.verify(service).backpackItemsPreview(backpackID);
    }
}
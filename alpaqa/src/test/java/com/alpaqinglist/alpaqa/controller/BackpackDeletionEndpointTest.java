package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackDeleter;
import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackpackDeletionEndpointTest {
    @Autowired
    TestRestTemplate restTemplate;
    @MockBean
    BackpackDeleter backpackDeleter;

    @Test
    void deleteBackpack() {
        Long backpackID = 1L;
        String url = "/backpacks/" + backpackID;

        restTemplate.delete(url);
        Mockito.verify(backpackDeleter).delete(backpackID);

    }
}
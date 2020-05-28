package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.logic.BackpackCreator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackpackEndpointTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    BackpackCreator backpackCreator;

    @Value("${test.backpackEndpoint.url}")
    String url;

    @Test
    void createNewBackpack() {
        BackpackDTO backpackTDO = new BackpackDTO();

        testRestTemplate.postForObject(url, backpackTDO, void.class);

        verify(backpackCreator).create(Mockito.any());


    }

}
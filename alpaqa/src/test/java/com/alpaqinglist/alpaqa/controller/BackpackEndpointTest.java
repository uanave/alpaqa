package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.data.BackpackDTO;
import com.alpaqinglist.alpaqa.logic.BackpackCreator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        BackpackDTO backpackTDO = new BackpackDTO("test", "test");
        when(backpackCreator.create(backpackTDO))
                .thenReturn(Optional.of(backpackTDO));

        testRestTemplate.postForObject(url, backpackTDO, void.class);

        verify(backpackCreator).create(backpackTDO);


    }

}
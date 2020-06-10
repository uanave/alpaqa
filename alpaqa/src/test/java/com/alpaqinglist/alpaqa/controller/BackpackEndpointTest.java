package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.logic.BackpackEditor;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BackpackEndpointTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    BackpackEditor backpackEditor;

    Long backpackID = 1L;
    String itemsUrl = "/backpacks/" + backpackID + "/items";
    Long itemId = 1L;
    String itemUrl = itemsUrl + "/" + itemId;
    String backpackUrl = "/backpacks/" + backpackID;

    Backpack backpack = new Backpack();

    @Test
    void addBackpack() {
        Backpack backpack = new Backpack("test", "test");
        when(backpackEditor.create(backpack))
                .thenReturn(Optional.of(backpack));

        testRestTemplate.postForObject("/backpacks/", backpack, void.class);

        verify(backpackEditor).create(backpack);
    }

    @Test
    void getBackpack() {
        when(backpackEditor.getBackpack(backpackID))
                .thenReturn(backpack);
        testRestTemplate.getForObject(backpackUrl, Backpack.class);
        verify(backpackEditor).getBackpack(backpackID);
    }

    @Test
    void updateBackpack() {
        when(backpackEditor.updateBackpack(backpackID, backpack))
                .thenReturn(backpack);
        testRestTemplate.put(backpackUrl, backpack, Backpack.class);
        verify(backpackEditor).updateBackpack(backpackID, backpack);
    }

    @Test
    void deleteBackpack() {
        Long backpackID = 1L;
        String url = "/backpacks/" + backpackID;

        testRestTemplate.delete(url);
        verify(backpackEditor).delete(backpackID);

    }

    @Test
    void getAllBackpacks() {
    }
}
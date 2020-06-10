package com.alpaqinglist.alpaqa.logic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@SpringBootTest(webEnvironment = NONE)
class BackpackServiceTest {
    @Autowired
    BackpackService backpackService;

    @MockBean
    BackpackEditor backpackEditor;

    @MockBean
    ItemEditor itemEditor;

    @Test
    void getItem() {
        itemEditor.getItem(any());
        verify(itemEditor).getItem(any());
    }

    @Test
    void updateItem() {
        itemEditor.updateItem(any(), any());
        verify(itemEditor).updateItem(any(), any());
    }

    @Test
    void deleteItem() {
        itemEditor.deleteItem(any(), any());
        verify(itemEditor).deleteItem(any(), any());
    }

    @Test
    void getAllItems() {
        itemEditor.getAllItems(any());
        verify(itemEditor).getAllItems(any());
    }

    @Test
    void getBackpack() {
        backpackEditor.getBackpack(any());
        verify(backpackEditor).getBackpack(any());
    }

    @Test
    void updateBackpack() {
        backpackEditor.updateBackpack(any(), any());
        verify(backpackEditor).updateBackpack(any(), any());
    }

    @Test
    void deleteBackpack() {
        backpackEditor.delete(any());
        verify(backpackEditor).delete(any());
    }

    @Test
    void getAllBackpacks() {
        backpackEditor.getAll();
        verify(backpackEditor).getAll();
    }
}
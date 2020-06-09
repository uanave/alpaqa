package com.alpaqinglist.alpaqa.logic;

import com.alpaqinglist.alpaqa.AlpaqaApplication;
import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import com.alpaqinglist.alpaqa.persistence.repository.BackpackRepository;
import com.alpaqinglist.alpaqa.persistence.repository.ItemRepository;
import com.alpaqinglist.alpaqa.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BackpackDeleterTest {
    @Autowired
    BackpackDeleter backpackDeleter;
    @MockBean
    BackpackRepository repository;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    UserRepository userRepository;
    Long backpackID = 1L;

    @Test
    void delete() {
        Mockito.when(repository.findById(backpackID)).thenReturn(Optional.of(new Backpack()));

        backpackDeleter.delete(backpackID);

        Mockito.verify(repository).delete(Mockito.any());
    }
}
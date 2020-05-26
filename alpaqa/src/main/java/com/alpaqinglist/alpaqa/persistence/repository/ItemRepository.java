package com.alpaqinglist.alpaqa.persistence.repository;

import com.alpaqinglist.alpaqa.persistence.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}

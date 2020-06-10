package com.alpaqinglist.alpaqa.persistence.repository;

import com.alpaqinglist.alpaqa.persistence.domain.Backpack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BackpackRepository extends JpaRepository<Backpack, Long> {
    boolean existsByName(String backpackName);
    List<Backpack> findAllByOrderByIdDesc();
}

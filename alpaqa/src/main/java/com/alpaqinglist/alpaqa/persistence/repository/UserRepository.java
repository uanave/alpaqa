package com.alpaqinglist.alpaqa.persistence.repository;

import com.alpaqinglist.alpaqa.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

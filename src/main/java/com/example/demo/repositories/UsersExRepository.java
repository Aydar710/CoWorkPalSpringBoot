package com.example.demo.repositories;

import com.example.demo.models.UserEx;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersExRepository extends JpaRepository<UserEx, Long> {
    List<UserEx> findAllByName(String name);
}


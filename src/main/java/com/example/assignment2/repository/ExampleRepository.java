package com.example.assignment2.repository;

import com.example.assignment2.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExampleRepository extends JpaRepository<Example, Long> {
    List<Example> findByTitleContaining(String title);
}

package com.cursor.hw17.repository;

import com.cursor.hw17.entity.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotebookRepository extends JpaRepository<Laptop, Long> {
    List<Laptop> findByRamGreaterThan(int mem);

    List<Laptop> findByOrderByBrandAsc();

    List<Laptop> findByUsedIsTrue();
}
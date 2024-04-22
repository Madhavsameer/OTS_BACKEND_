package com.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.model.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByActiveTrue();
}

package com.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.model.StudentGrade;

import java.util.List;

public interface StudentGradeRepository extends JpaRepository<StudentGrade, Long> {
    List<StudentGrade> findAllByRegNo(String regNo);
}



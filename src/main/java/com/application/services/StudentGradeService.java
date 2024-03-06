package com.application.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.model.StudentGrade;
import com.application.repository.StudentGradeRepository;

import java.util.List;

@Service
public class StudentGradeService {
    @Autowired
    private StudentGradeRepository studentGradeRepository;

    public List<StudentGrade> getDataByRegNo(String regNo) {
        return studentGradeRepository.findAllByRegNo(regNo);
    }
}



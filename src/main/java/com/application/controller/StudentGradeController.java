package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.StudentGrade;
import com.application.services.StudentGradeService;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
public class StudentGradeController {
    @Autowired
    private StudentGradeService studentGradeService;

    @GetMapping("/grades/{regNo}")
    public List<StudentGrade> getData(@PathVariable String regNo) {
        return studentGradeService.getDataByRegNo(regNo);
    }
}



package com.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.Test;
import com.application.repository.TestRepository;

@RestController
@RequestMapping("/api/tests")
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {
    @Autowired
    private TestRepository testRepository;
    
    @PostMapping("/schedule")
    public ResponseEntity<Test> scheduleTest(@RequestBody Test test) {
        test.setActive(true);
        Test savedTest = testRepository.save(test);
        return ResponseEntity.ok(savedTest);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Test>> getActiveTests() {
        List<Test> activeTests = testRepository.findByActiveTrue();
        return ResponseEntity.ok(activeTests);
    }
}


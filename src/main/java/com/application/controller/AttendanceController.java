package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.model.AttendanceRecord;
import com.application.repository.AttendanceRecordRepository;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {
    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @GetMapping("/{name}")
    public ResponseEntity<List<AttendanceRecord>> getAttendanceByName(@PathVariable("name") String name) {
        List<AttendanceRecord> attendanceRecords = attendanceRecordRepository.findByParticipantNameIgnoreCase(name);
        if (attendanceRecords.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(attendanceRecords, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<AttendanceRecord>> getAllAttendance() {
        List<AttendanceRecord> attendanceRecords = attendanceRecordRepository.findAll();
        if (attendanceRecords.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(attendanceRecords, HttpStatus.OK);
    }
}



package com.application.model;

import java.time.LocalDateTime;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String testName;
    private LocalDateTime dateTime;
    private int durationMinutes;
    private boolean active;
    private String testLink;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public int getDurationMinutes() {
		return durationMinutes;
	}
	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
    
    public String getTestLink() {
		return testLink;
	}
	public void setTestLink(String testLink) {
		this.testLink = testLink;
	}
	// constructors, getters, setters
	public Test() {
        // Default constructor for Jackson deserialization
    }

    // Parameterized constructor
    public Test(String testName, LocalDateTime dateTime, int durationMinutes, boolean active, String testLink) {
        this.testName = testName;
        this.dateTime = dateTime;
        this.durationMinutes = durationMinutes;
        this.active = active;
        this.testLink=testLink;
    }

    // Getters and setters
    // Omitted for brevity
}



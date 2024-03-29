package com.application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.model.Assignment;

@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, String> {
}



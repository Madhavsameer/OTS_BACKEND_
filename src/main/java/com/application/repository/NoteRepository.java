package com.application.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
}



package com.application.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.application.model.Note;
import com.application.repository.NoteRepository;
import com.mongodb.client.gridfs.model.GridFSFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:3000")
public class NoteController {

    @Autowired
    private GridFsOperations gridFsOperations;

    @Autowired
    private NoteRepository noteRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("title") String title) {
        try {
            ObjectId fileId = gridFsOperations.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
            Note note = new Note();
            note.setTitle(title);
            note.setFileId(fileId.toHexString()); // Store ObjectId as String
            noteRepository.save(note);
            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        String fileId = note.getFileId();
        if (fileId != null) {
            try {
                GridFSFile gridFSFile = gridFsOperations.findOne(new Query().addCriteria(Criteria.where("_id").is(new ObjectId(fileId))));
                if (gridFSFile == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                }
                InputStream stream = gridFsOperations.getResource(gridFSFile).getInputStream();
                byte[] content = stream.readAllBytes();
                return ResponseEntity.ok().body(content);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        return ResponseEntity.ok().body(notes);
    }
}

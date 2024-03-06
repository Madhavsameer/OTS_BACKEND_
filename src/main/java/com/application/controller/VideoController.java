package com.application.controller;

import com.application.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = {"http://localhost:3000","https://onlinetutorialsystem.netlify.app"})
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file) throws IOException {
        videoService.uploadVideo(file);
        return ResponseEntity.ok("Video uploaded successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllVideos() {
        List<String> videoUrls = videoService.getUploadedVideoUrls();
        return ResponseEntity.ok(videoUrls);
    }
}


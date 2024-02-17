package com.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.model.Video;
import com.application.repository.VideoRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {

    @Value("${upload.folder.path}")
    private String uploadFolderPath;

    @Autowired
    private VideoRepository videoRepository;

    public Video uploadVideo(MultipartFile file) {
        try {
            Video video = new Video();
            video.setName(file.getOriginalFilename());
            String httpServer="http://localhost:8081";
            String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            String url= httpServer+File.separator+fileName;
            
            String filePath = uploadFolderPath + File.separator + fileName;
            video.setUrl(url);
            // Save the video metadata to database
            Video savedVideo = videoRepository.save(video);
            // Save the video file to file system
            File destFile = new File(filePath);
            file.transferTo(destFile);
            return savedVideo;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload video: " + e.getMessage());
        }
    }

    // Other methods...


    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);
    }
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }
}




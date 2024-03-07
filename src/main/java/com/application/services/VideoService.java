package com.application.services;
import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {

    @Value("${firebase.credentials.path}")
    private String credentialsPath;

    @Value("${firebase.storage.bucket}")
    private String bucketName;

    public void uploadVideo(MultipartFile file) throws IOException {
        // Initialize Firebase Storage client
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(credentialsPath)))
                .build()
                .getService();

        // Generate a unique filename for the uploaded video
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Upload video file to Firebase Storage
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, file.getBytes());
    }

    public List<String> getUploadedVideoUrls() {
        try {
            // Initialize Firebase Storage client
            Storage storage = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream(credentialsPath)))
                    .build()
                    .getService();

            // List all objects (files) in the bucket
            List<String> urls = new ArrayList<>();
            Page<Blob> blobs = storage.list(bucketName);
            for (Blob blob : blobs.iterateAll()) {
                // Get the URL of each object (file) in the bucket
                String url = blob.getMediaLink();
                urls.add(url);
            }
            return urls;
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch uploaded videos: " + e.getMessage());
        }
    }
}


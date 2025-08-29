package com.app.rekognition.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.time.Instant;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucket = "app-caserta-2025";

    public S3Service() {
        this.s3Client = S3Client.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = Instant.now().getEpochSecond() + "_" + file.getOriginalFilename();
        String path = "imagens/" + fileName;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(path)
                .contentType(file.getContentType())
                .acl("public-read")
                .build();


        s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

        return "https://" + bucket + ".s3.amazonaws.com/" + path;
    }

    public String mockUploadImage(String fileName) {
        String path = "imagens/" + fileName;

        return "https://" + bucket + ".s3.amazonaws.com/" + path;
    }

}

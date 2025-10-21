package com.app.rekognition.demo.infrastructure.aws;

import com.app.rekognition.demo.application.ports.out.StorageContentPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.time.Instant;

@Component
public class S3StorageAdapter implements StorageContentPort {

    private final S3Client s3Client;
    @Value("${aws.s3.bucket-name}")
    private String bucket;

    @Value("${aws.s3.subdirectory}")
    private String subdirectory;

    public S3StorageAdapter(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public String uploadContent(MultipartFile file) throws IOException {
        String fileName = Instant.now().getEpochSecond() + "_" + file.getOriginalFilename();
        String path = subdirectory + fileName;

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(path)
                .contentType(file.getContentType())
                .acl("public-read")
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

        return "https://" + bucket + ".s3.amazonaws.com/" + path;
    }
}

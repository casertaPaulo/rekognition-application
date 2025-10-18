package com.app.rekognition.demo.infra;

import com.app.rekognition.demo.infrastructure.aws.S3StorageAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This is an integration test
 * Tests the unit responsible for uploading an image to S3Storage (AWS)
 *
 */
@SpringBootTest
public class S3StorageAdapterTest {

    @Autowired
    private S3StorageAdapter s3StorageAdapter;

    @Test
    void shouldUploadFileToS3() throws IOException {
        MultipartFile file = new MockMultipartFile("file", "test.png", "image/png", "bytes".getBytes());

        String url = s3StorageAdapter.uploadContent(file);

        assertTrue(url.contains("s3"));
    }

}

package com.app.rekognition.demo.domain.port;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageContentPort {
    String uploadContent(MultipartFile file) throws IOException;
}

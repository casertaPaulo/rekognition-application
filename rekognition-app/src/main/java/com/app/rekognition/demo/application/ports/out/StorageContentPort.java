package com.app.rekognition.demo.application.ports.out;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageContentPort {
    String uploadContent(MultipartFile file) throws IOException;
}

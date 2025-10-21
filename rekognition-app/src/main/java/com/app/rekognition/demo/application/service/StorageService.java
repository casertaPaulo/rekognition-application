package com.app.rekognition.demo.application.service;

import com.app.rekognition.demo.application.ports.out.StorageContentPort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
    public class StorageService {

    private final StorageContentPort storageContentPort;

    public StorageService(StorageContentPort storageContentPort) {
        this.storageContentPort = storageContentPort;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        return storageContentPort.uploadContent(file);
    }

    public String returnsMockUrlToOnlineContent(String fileName) {
        String path = "imagens/" + fileName;

        return "https://" + "app-caserta-2025" + ".s3.amazonaws.com/" + path;
    }

}

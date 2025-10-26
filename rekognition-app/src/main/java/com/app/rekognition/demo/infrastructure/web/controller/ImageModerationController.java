package com.app.rekognition.demo.infrastructure.web.controller;

import com.app.rekognition.demo.infrastructure.web.dto.ImageListResponse;
import com.app.rekognition.demo.infrastructure.web.dto.ApplicationResponse;
import com.app.rekognition.demo.application.service.ImageAnalyzerService;
import com.app.rekognition.demo.application.service.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/image")
public class ImageModerationController {

    @Autowired
    private ImageAnalyzerService imageAnalyzerService;

    @Autowired
    private PersistenceService imageService;


    @PostMapping
    public ResponseEntity<ApplicationResponse> analyzeImage(@RequestParam("file") MultipartFile file) throws IOException {
       return ResponseEntity.ok(imageAnalyzerService.analyzeImage(file));
    }

    @GetMapping
    public ResponseEntity<ImageListResponse> getAll() {
        return ResponseEntity.ok(imageService.getAll());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        imageService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

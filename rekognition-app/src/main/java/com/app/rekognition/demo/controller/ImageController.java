package com.app.rekognition.demo.controller;

import com.app.rekognition.demo.DTO.ImageListDTO;
import com.app.rekognition.demo.DTO.ImageResponseDTO;
import com.app.rekognition.demo.service.ImageAnalyzerService;
import com.app.rekognition.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageAnalyzerService imageAnalyzerService;

    @Autowired
    private ImageService imageService;


    @PostMapping
    public ResponseEntity<ImageResponseDTO> analyzeImage(@RequestParam("file") MultipartFile file) {
       return ResponseEntity.ok(imageAnalyzerService.analyzeImage(file));
    }

    @GetMapping
    public ResponseEntity<ImageListDTO> getAll() {
        return ResponseEntity.ok(imageService.getAll());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        imageService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

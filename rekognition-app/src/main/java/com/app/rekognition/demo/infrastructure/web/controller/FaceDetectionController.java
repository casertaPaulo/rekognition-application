package com.app.rekognition.demo.infrastructure.web.controller;

import com.app.rekognition.demo.application.service.FaceDetectionService;
import com.app.rekognition.demo.infrastructure.web.dto.faces.FaceResponse;
import com.app.rekognition.demo.infrastructure.web.dto.faces.SearchFaceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/face-detection")
public class FaceDetectionController {

    @Autowired
    private FaceDetectionService service;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addToCollection(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(service.addToCollection(file));
    }

    @PostMapping("/search")
    public ResponseEntity<List<SearchFaceResponse>> searchFaceByImage(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(service.searchFaceInCollection(file));
    }

    @GetMapping
    public ResponseEntity<List<FaceResponse>> getFacesCollection() {
        return ResponseEntity.ok(service.listFacesCollection());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllFaceCollection() {
        service.deleteFacesCollection();
        return ResponseEntity.ok("All faces was deleted from collection.");
    }
}

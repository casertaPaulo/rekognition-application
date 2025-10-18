package com.app.rekognition.demo.domain.service;

import com.app.rekognition.demo.api.dto.ApplicationResponse;
import com.app.rekognition.demo.api.dto.ModerationLabels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageAnalyzerService {

    @Autowired
    private ContentModerationService contentModerationService;

    @Autowired
    private StorageService storageService;

    public ApplicationResponse analyzeImage(MultipartFile file) throws IOException {

        // Todo: implement logic
        ModerationLabels labels = contentModerationService.analyze(file);

        return new ApplicationResponse(
                contentModerationService.evaluateLabel(labels),"testing.caserta");
    }
}

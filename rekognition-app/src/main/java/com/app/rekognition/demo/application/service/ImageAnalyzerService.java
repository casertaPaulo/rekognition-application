package com.app.rekognition.demo.application.service;

import com.app.rekognition.demo.application.ports.out.StorageContentPort;
import com.app.rekognition.demo.domain.enums.ModerationStatus;
import com.app.rekognition.demo.infrastructure.web.dto.ApplicationResponse;
import com.app.rekognition.demo.application.dto.ModerationLabels;
import com.app.rekognition.demo.infrastructure.web.dto.ModerationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageAnalyzerService {

    @Autowired
    private ContentModerationService contentModerationService;

    @Autowired
    private StorageContentPort storageContentPort;

    @Autowired
    private PersistenceService persistenceService;

    public ApplicationResponse analyzeImage(MultipartFile file) throws IOException {

        ModerationLabels labels = contentModerationService.getModerationLabels(file);

        ModerationResult result = contentModerationService.evaluateLabel(labels);

        // Se aprovado, guardar no S3 e no POSTGRESQL
        if (result.status() == ModerationStatus.APPROVED) {
            String urlToImageInS3 = storageContentPort.uploadContent(file);
            persistenceService.saveImageUrl(urlToImageInS3);
            return new ApplicationResponse(result, urlToImageInS3);
        }

        return new ApplicationResponse(result, "");
    }
}

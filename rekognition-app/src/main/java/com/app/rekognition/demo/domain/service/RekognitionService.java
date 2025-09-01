package com.app.rekognition.demo.domain.service;

import com.app.rekognition.demo.exception.RekognitionApiException;
import com.app.rekognition.demo.infra.RekognitionAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectModerationLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectModerationLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;
import software.amazon.awssdk.services.rekognition.model.RekognitionException;

import java.io.IOException;

@Service
public class RekognitionService {

    private final RekognitionAdapter rekognitionAdapter;

    public RekognitionService(RekognitionAdapter rekognitionAdapter) {
        this.rekognitionAdapter = rekognitionAdapter;
    }

    public DetectModerationLabelsResponse detectModeration(MultipartFile file) throws IOException {
        return rekognitionAdapter.detectModeration(file.getBytes());
    }
}

package com.app.rekognition.demo.domain.service;

import com.app.rekognition.demo.exception.RekognitionApiException;
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

    private final RekognitionClient rekognitionClient;

    public RekognitionService() {
        System.out.println("👌Rekognition service created");
        this.rekognitionClient = RekognitionClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    public DetectModerationLabelsResponse detectModeration(MultipartFile file) throws IOException {
        try {
            SdkBytes imageBytes = SdkBytes.fromInputStream(file.getInputStream());

            Image image = Image.builder().bytes(imageBytes).build();

            DetectModerationLabelsRequest request = DetectModerationLabelsRequest.builder()
                    .image(image)
                    .minConfidence(50F)
                    .build();

            return rekognitionClient.detectModerationLabels(request);

        } catch (RekognitionException e) {
            throw new RekognitionApiException(e.getMessage());
        }
    }
}

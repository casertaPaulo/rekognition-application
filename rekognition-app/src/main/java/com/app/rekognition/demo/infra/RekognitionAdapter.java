package com.app.rekognition.demo.infra;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectModerationLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectModerationLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;

@Component
public class RekognitionAdapter {

    private final RekognitionClient rekognitionClient;

    public RekognitionAdapter(RekognitionClient rekognitionClient) {
        this.rekognitionClient = rekognitionClient;
    }

    public DetectModerationLabelsResponse detectModeration(byte[] fileBytes) {
        try {
            SdkBytes imageBytes = SdkBytes.fromByteArray(fileBytes);
            Image image = Image.builder().bytes(imageBytes).build();

            // Build the structure rekognition service request
            DetectModerationLabelsRequest request = DetectModerationLabelsRequest.builder()
                    .image(image)
                    .minConfidence(50F)
                    .build();

            return rekognitionClient.detectModerationLabels(request);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

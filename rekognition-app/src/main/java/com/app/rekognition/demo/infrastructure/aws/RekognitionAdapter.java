package com.app.rekognition.demo.infrastructure.aws;

import com.app.rekognition.demo.api.dto.ModerationLabel;
import com.app.rekognition.demo.api.dto.ModerationLabels;
import com.app.rekognition.demo.domain.port.ContentModerationPort;
import com.app.rekognition.demo.exception.RekognitionApiException;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectModerationLabelsRequest;
import software.amazon.awssdk.services.rekognition.model.DetectModerationLabelsResponse;
import software.amazon.awssdk.services.rekognition.model.Image;

import java.util.List;

@Component
public class RekognitionAdapter implements ContentModerationPort {

    private final RekognitionClient rekognitionClient;

    public RekognitionAdapter(RekognitionClient rekognitionClient) {
        this.rekognitionClient = rekognitionClient;
    }

    @Override
    public ModerationLabels detectModeration(byte[] fileBytes) {
        try {
            SdkBytes imageBytes = SdkBytes.fromByteArray(fileBytes);
            Image image = Image.builder().bytes(imageBytes).build();

            // Build the structure rekognition service request
            DetectModerationLabelsRequest request = DetectModerationLabelsRequest.builder()
                    .image(image)
                    .minConfidence(1F) // Todo: fix that, hard code
                    .build();

            DetectModerationLabelsResponse response = rekognitionClient.detectModerationLabels(request);

            List<ModerationLabel> moderationLabels = response.moderationLabels().stream()
                    .map(l -> new ModerationLabel(
                            l.name(),
                            l.parentName(),
                            l.confidence(),
                            l.taxonomyLevel())).toList();

            return new ModerationLabels(moderationLabels);
        } catch (Exception e) {
            throw new RekognitionApiException(e.getMessage());
        }
    }

}

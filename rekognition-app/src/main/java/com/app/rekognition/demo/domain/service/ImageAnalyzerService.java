package com.app.rekognition.demo.domain.service;

import com.app.rekognition.demo.api.dto.ImageResponseDTO;
import com.app.rekognition.demo.api.dto.ModerationLabelDTO;
import com.app.rekognition.demo.api.controller.ImageNotificationController;
import com.app.rekognition.demo.exception.RekognitionApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.rekognition.model.DetectModerationLabelsResponse;

@Service
public class ImageAnalyzerService {

    @Autowired
    private RekognitionService service;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageNotificationController notificationController;

    @Autowired
    private ImageService imageService;

    public ImageResponseDTO analyzeImage(MultipartFile file) {

        try {
            DetectModerationLabelsResponse response = service.detectModeration(file);

            var result = response.moderationLabels().stream().map(
                    label -> new ModerationLabelDTO(
                            label.name(),
                            label.parentName(),
                            label.confidence()
                    )
            ).toList();

            if (result.isEmpty()) {
                // salva no s3 e gera um url para a imagem
                String imageUrl = s3Service.uploadImage(file);

                // salva a url gerada no db
                //imageService.saveImageUrl(imageUrl);

                // manda uma notificação via websocket para a nova imagem salva
                notificationController.sendImageNotification(imageUrl);
                return new ImageResponseDTO(true, result, imageUrl);
            }

            return new ImageResponseDTO(false, result, null);

        } catch (Exception e) {
            throw new RekognitionApiException(e.getMessage());
        }
    }
}

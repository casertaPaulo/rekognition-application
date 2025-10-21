package com.app.rekognition.demo.application.service;

import com.app.rekognition.demo.application.dto.ModerationLabel;
import com.app.rekognition.demo.application.dto.ModerationLabels;
import com.app.rekognition.demo.infrastructure.web.dto.ModerationResult;
import com.app.rekognition.demo.domain.enums.ModerationStatus;
import com.app.rekognition.demo.application.ports.out.ContentModerationPort;
import com.app.rekognition.demo.domain.exception.FileInvalidTypeException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ContentModerationService {

    private final ContentModerationPort contentModerationPort;

    public ContentModerationService(ContentModerationPort contentModerationPort) {
        this.contentModerationPort = contentModerationPort;
    }

    // Retorna uma lista de labels de moderação do Adapter [AWS Rekognition]
    public ModerationLabels analyze(MultipartFile file) throws IOException {
        // Verifica se o arquivo é uma imagem
        if (file.getContentType() == null || !file.getContentType().startsWith("image/"))
            throw new FileInvalidTypeException();

        return contentModerationPort.detectModeration(file.getBytes());
    }


    // Todo: Desenvolver o método de filtro de labels
    public ModerationResult evaluateLabel(ModerationLabels moderationLabels) {
        Map<String, Float> blockRules = Map.of( // Todo: put this on a database for reactive changes
          "Violence", 50f
        );

        List<ModerationLabel> violations = new ArrayList<>();

        for (ModerationLabel label : moderationLabels.labels()) {
            if (blockRules.containsKey(label.name())) {
                float threshold = blockRules.get(label.name());

                if (label.confidence() >= threshold) {
                    violations.add(label);
                }
            }
        }

        ModerationStatus status = violations.isEmpty() ? ModerationStatus.APPROVED : ModerationStatus.REJECTED;

        return new ModerationResult(status, moderationLabels);
    }

}

package com.app.rekognition.demo.infrastructure.web.dto;

import com.app.rekognition.demo.application.dto.ModerationLabels;
import com.app.rekognition.demo.domain.enums.ModerationStatus;

/**
 * Record that represents final image processing
 *
 *
 */
public record ModerationResult(
        ModerationStatus status,
        ModerationLabels moderation
) { }


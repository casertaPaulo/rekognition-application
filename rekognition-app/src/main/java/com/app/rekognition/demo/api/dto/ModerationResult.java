package com.app.rekognition.demo.api.dto;

import com.app.rekognition.demo.domain.model.enums.ModerationStatus;

/**
 * Record that represents final image processing
 *
 *
 */
public record ModerationResult(
        ModerationStatus status,
        ModerationLabels moderation
) { }


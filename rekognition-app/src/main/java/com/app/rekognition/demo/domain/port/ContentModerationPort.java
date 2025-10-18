package com.app.rekognition.demo.domain.port;

import com.app.rekognition.demo.api.dto.ModerationLabels;

public interface ContentModerationPort {
    ModerationLabels detectModeration(byte[] fileBytes);
}

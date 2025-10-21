package com.app.rekognition.demo.application.ports.out;

import com.app.rekognition.demo.application.dto.ModerationLabels;

public interface ContentModerationPort {
    ModerationLabels detectModeration(byte[] fileBytes);
}

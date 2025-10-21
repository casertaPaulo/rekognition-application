package com.app.rekognition.demo.infrastructure.web.dto;

public record ApplicationResponse(
        ModerationResult moderationResponse,
        String urlToImage
) { }

package com.app.rekognition.demo.api.dto;

public record ApplicationResponse(
        ModerationResult moderationResponse,
        String urlToImage
) { }

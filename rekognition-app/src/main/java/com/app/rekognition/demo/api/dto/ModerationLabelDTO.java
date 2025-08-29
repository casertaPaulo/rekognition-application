package com.app.rekognition.demo.api.dto;

public record ModerationLabelDTO(
        String name,
        String parentName,
        Float confidence
) { }

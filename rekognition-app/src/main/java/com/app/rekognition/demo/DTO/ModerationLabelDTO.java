package com.app.rekognition.demo.DTO;

public record ModerationLabelDTO(
        String name,
        String parentName,
        Float confidence
) { }

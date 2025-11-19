package com.app.rekognition.demo.infrastructure.web.dto.faces;

public record FaceResponse(
    String faceId,
    Float confidence
) { }

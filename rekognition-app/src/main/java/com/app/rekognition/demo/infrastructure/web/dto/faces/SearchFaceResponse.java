package com.app.rekognition.demo.infrastructure.web.dto.faces;

public record SearchFaceResponse(
        FaceResponse faceResponse,
        Float similarity
) { }

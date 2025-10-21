package com.app.rekognition.demo.infrastructure.web.dto;

import java.util.List;

public record ImageListResponse(
        List<String> imagesUrl
) { }

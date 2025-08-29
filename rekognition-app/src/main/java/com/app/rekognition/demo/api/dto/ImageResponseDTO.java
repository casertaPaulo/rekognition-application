package com.app.rekognition.demo.api.dto;

import java.util.List;

public record ImageResponseDTO(
        Boolean imageIsPermitted,
        List<ModerationLabelDTO> moderationsList,
        String urlToImage
) { }

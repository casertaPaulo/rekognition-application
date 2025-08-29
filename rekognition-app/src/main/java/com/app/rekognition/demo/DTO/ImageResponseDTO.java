package com.app.rekognition.demo.DTO;

import java.util.List;

public record ImageResponseDTO(
        Boolean imageIsPermitted,
        List<ModerationLabelDTO> moderationsList,
        String urlToImage
) { }

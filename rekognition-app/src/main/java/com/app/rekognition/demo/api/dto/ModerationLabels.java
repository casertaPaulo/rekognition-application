package com.app.rekognition.demo.api.dto;

import java.util.List;

/**
 * Representa uma lista de labels de moderação
 *
 *
 */
public record ModerationLabels(
        List<ModerationLabel> labels
) { }

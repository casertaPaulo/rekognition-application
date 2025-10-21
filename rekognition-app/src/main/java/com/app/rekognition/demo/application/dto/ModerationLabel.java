package com.app.rekognition.demo.application.dto;

/**
 * Representa o label retornado pelo AWS Rekognition
 *
 *
 */
public record ModerationLabel(
        String name,
        String parentName,
        Float confidence,
        Integer taxonomyLevel
) { }

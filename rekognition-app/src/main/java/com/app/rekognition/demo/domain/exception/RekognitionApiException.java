package com.app.rekognition.demo.domain.exception;

import org.springframework.http.HttpStatus;

public class RekognitionApiException extends AppException {
    public RekognitionApiException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error" ,message);
    }
}

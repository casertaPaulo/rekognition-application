package com.app.rekognition.demo.exception;

import org.springframework.http.HttpStatus;

public class RekognitionApiException extends AppException {

    public RekognitionApiException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}

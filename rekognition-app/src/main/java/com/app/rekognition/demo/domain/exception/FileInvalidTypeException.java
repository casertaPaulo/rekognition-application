package com.app.rekognition.demo.domain.exception;

import org.springframework.http.HttpStatus;

public class FileInvalidTypeException extends AppException {

    public FileInvalidTypeException() {
        super(HttpStatus.BAD_REQUEST, "Bad Request", "File must be a image.");
    }
}

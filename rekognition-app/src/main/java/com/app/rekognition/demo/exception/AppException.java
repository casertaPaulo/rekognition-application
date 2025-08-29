package com.app.rekognition.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AppException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String exceptionTitle;

    public AppException(HttpStatus statusCode, String exceptionTitle) {
        this.statusCode = statusCode;
        this.exceptionTitle = exceptionTitle;
    }

    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(statusCode);
        problemDetail.setTitle(exceptionTitle);

        return problemDetail;
    }


}

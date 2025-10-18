package com.app.rekognition.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AppException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String exceptionTitle;
    private final String exceptionDetail;

    public AppException(HttpStatus statusCode, String exceptionTitle, String exceptionDetail) {
        this.statusCode = statusCode;
        this.exceptionTitle = exceptionTitle;
        this.exceptionDetail = exceptionDetail;
    }

    public ProblemDetail toProblemDetail() {
        var problemDetail = ProblemDetail.forStatus(statusCode);
        problemDetail.setTitle(exceptionTitle);
        problemDetail.setDetail(exceptionDetail);

        return problemDetail;
    }
}

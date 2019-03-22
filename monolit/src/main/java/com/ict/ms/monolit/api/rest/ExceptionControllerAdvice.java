package com.ict.ms.monolit.api.rest;

import com.ict.ms.monolit.api.rest.dto.ApiError;
import com.ict.ms.monolit.domain.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ApiError> handleMissingProject(
            ProjectNotFoundException exception,
            WebRequest webRequest){

        ApiError error = ApiError.builder().status(HttpStatus.NOT_FOUND)
                .message(exception.getLocalizedMessage())
                .build();

       return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND );

    }
}

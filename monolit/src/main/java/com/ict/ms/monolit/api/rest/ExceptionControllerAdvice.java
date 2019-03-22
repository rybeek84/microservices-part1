package com.ict.ms.monolit.api.rest;

import com.ict.ms.monolit.api.rest.dto.ApiError;
import com.ict.ms.monolit.domain.exception.InvalidProjectMemberException;
import com.ict.ms.monolit.domain.exception.ProjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {


    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ApiError> handleMissingProject(
            ProjectNotFoundException exception){

        ApiError error = ApiError.builder().status(HttpStatus.NOT_FOUND)
                .message(exception.getLocalizedMessage())
                .build();

       return new ResponseEntity<>(error, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(InvalidProjectMemberException.class)
    public ResponseEntity<ApiError> handleMissingProject(
            InvalidProjectMemberException exception){

        ApiError error = ApiError.builder().status(HttpStatus.FORBIDDEN)
                .message(exception.getLocalizedMessage())
                .build();

        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}

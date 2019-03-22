package com.ict.ms.monolit.controller.advice;

import com.ict.ms.monolit.controller.dto.ApiError;
import com.ict.ms.monolit.domain.exception.ProjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<Object> handleMissingProject(
            ProjectNotFoundException exception,
            WebRequest webRequest){

        ApiError error = ApiError.builder().status(HttpStatus.NOT_FOUND)
                .message(exception.getLocalizedMessage())
                .build();

       return handleExceptionInternal(
               exception,
               error,
               new HttpHeaders(),
               HttpStatus.NOT_FOUND, webRequest);

    }
}

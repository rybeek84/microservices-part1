package com.ict.ms.monolit.domain.exception;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Long id){
        super("There is no project in database for given id: " + id);
    }
}

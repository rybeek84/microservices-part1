package com.ict.ms.monolit.domain.exception;

public class InvalidProjectMemberException extends RuntimeException {
    public InvalidProjectMemberException(String userEmail) {
        super("User '" + userEmail + "' is not a member of selected project");
    }
}

package com.ict.ms.monolit.domain.exception;

import java.util.UUID;

public class TaskNotFoundException extends RuntimeException{

    public TaskNotFoundException(UUID uuid) {
        super("There is no task with uuid: " + uuid);
    }
}

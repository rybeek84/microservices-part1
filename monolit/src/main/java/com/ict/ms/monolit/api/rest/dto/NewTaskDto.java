package com.ict.ms.monolit.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NewTaskDto {
    private String name;
    private String description;
    private UserEmail assignedTo;
    private LocalDateTime dueDate;

    @JsonCreator
    NewTaskDto(){}

    @Builder
    private NewTaskDto(String name, String description, UserEmail assignedTo, LocalDateTime dueDate) {
        this.name = name;
        this.description = description;
        this.assignedTo = assignedTo;
        this.dueDate = dueDate;
    }
}

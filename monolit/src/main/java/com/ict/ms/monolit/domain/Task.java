package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.vo.UserId;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
public class Task {
    private String name;
    private String description;
    private Status status;
    private LocalDateTime dueDate;
    private UserId author;
    private UserId assignedTo;
}

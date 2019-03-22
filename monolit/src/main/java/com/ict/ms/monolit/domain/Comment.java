package com.ict.ms.monolit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime createdAt;
    private UserEmail createdBy;
    private String text;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Task task;
}

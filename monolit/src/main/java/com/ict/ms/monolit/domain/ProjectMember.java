package com.ict.ms.monolit.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProjectMember{
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Project project;

    private UserEmail user;

    private boolean allowedCreateTask;
    private boolean allowedUpdateTask;
    private boolean allowedDeleteTask;

    ProjectMember(){}

    @Builder
    public ProjectMember(Project project, UserEmail user, boolean allowedCreateTask, boolean allowedUpdateTask, boolean allowedDeleteTask) {
        this.project = project;
        this.user = user;
        this.allowedCreateTask = allowedCreateTask;
        this.allowedUpdateTask = allowedUpdateTask;
        this.allowedDeleteTask = allowedDeleteTask;
    }

    public boolean hasSameEmail(String email){
        return user.getEmail().equalsIgnoreCase(email);
    }


}

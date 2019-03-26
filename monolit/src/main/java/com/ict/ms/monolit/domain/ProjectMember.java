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


    ProjectMember(){}

    @Builder
    private ProjectMember(Project project, UserEmail user) {
        this.project = project;
        this.user = user;
    }

    protected boolean emailMatch(String email){
        return user.getEmail().equalsIgnoreCase(email);
    }


}

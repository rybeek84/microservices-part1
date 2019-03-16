package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class ProjectMember{
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    private Project project;

    @ManyToOne(optional = false)
    private User user;

    private boolean allowedCreateTask;
    private boolean allowedUpdateTask;
    private boolean allowedDeleteTask;


}

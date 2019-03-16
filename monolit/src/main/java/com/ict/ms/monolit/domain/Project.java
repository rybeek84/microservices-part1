package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue
    private Long id;
    private UserEmail owner;
    private String name;
    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Task> tasks;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProjectMember> members;
}

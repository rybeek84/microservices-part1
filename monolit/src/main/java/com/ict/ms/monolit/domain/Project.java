package com.ict.ms.monolit.domain;

import lombok.Getter;

import javax.persistence.Entity;
import java.util.Set;

@Entity
@Getter
public class Project {
    private String name;
    private Set<Task> tasks;
}

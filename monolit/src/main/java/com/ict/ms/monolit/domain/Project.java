package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.exception.TaskNotFoundException;
import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(of="id")
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @AttributeOverride(name = "email", column = @Column(name="owner"))
    private UserEmail owner;
    private String name;
    private Status status = Status.NEW;
    private UUID uuid;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProjectMember> members = new HashSet<>();

    public boolean canCreateTask(String userEmail) {
        return members.stream().filter(members -> members.hasSameEmail(userEmail))
                .findFirst()
                .map(member -> member.isAllowedCreateTask())
                .orElse(false);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task findTask(UUID uuid) {
        return tasks.stream().filter(task -> task.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(uuid));
    }

    public void addMember(ProjectMember member) {
        this.members.add(member);
    }
}

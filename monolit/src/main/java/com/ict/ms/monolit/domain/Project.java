package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.exception.TaskNotFoundException;
import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode(of="id")
public class Project{

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
    private Set<Task> tasks;

    @OneToMany(
            mappedBy = "project",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ProjectMember> members;

    Project(){
        members = new HashSet<>();
        tasks = new HashSet<>();
    }

    @Builder
    public Project(UserEmail owner, String name) {
        this();
        this.owner = owner;
        this.name = name;
        this.uuid = UUID.randomUUID();
        this.status = Status.NEW;
        addMember(ProjectMember.builder()
                .user(owner)
                .project(this)
                .build());
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        task.addToProject(this);
    }

    public Task findTask(UUID uuid) {
        return tasks.stream().filter(task -> task.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException(uuid));
    }

    public void addMember(ProjectMember member) {
        this.members.add(member);
    }

    public boolean isMember(UserEmail user){
        return members.stream().anyMatch(member -> member.getUser().equals(user));
    }


}

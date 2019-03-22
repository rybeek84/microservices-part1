package com.ict.ms.monolit.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Status status;
    private LocalDateTime dueDate;

    @ReadOnlyProperty
    private UUID uuid;

    @AttributeOverride(name="email", column = @Column(name="author"))
    private UserEmail author;

    @AttributeOverride(name="email", column = @Column(name="assignedTo"))
    private UserEmail assignedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    @JsonIgnore
    private Project project;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}

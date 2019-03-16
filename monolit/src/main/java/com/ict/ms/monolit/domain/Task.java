package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.vo.UserEmail;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @AttributeOverride(name="email", column = @Column(name="author"))
    private UserEmail author;

    @AttributeOverride(name="email", column = @Column(name="assignedTo"))
    private UserEmail assignedTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId")
    private Project project;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}

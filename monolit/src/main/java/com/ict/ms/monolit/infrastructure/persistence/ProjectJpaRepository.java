package com.ict.ms.monolit.infrastructure.persistence;

import com.ict.ms.monolit.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
    Optional<Project> findById(Long id);
}

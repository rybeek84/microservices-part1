package com.ict.ms.monolit.domain;

import com.ict.ms.monolit.domain.exception.ProjectNotFoundException;
import com.ict.ms.monolit.infrastructure.persistence.ProjectJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectDomainRepository {

    ProjectJpaRepository projectJpaRepository;

    @Autowired
    public ProjectDomainRepository(ProjectJpaRepository projectJpaRepository) {
        this.projectJpaRepository = projectJpaRepository;
    }

    public Optional<Project> findById(Long id){
        return projectJpaRepository.findById(id);
    }

    public Project save(Project project){
        return projectJpaRepository.save(project);
    }

    public List<Project> findAll() {
        return projectJpaRepository.findAll();
    }

    public void delete(Long id) {
        projectJpaRepository.delete(id);
    }
}

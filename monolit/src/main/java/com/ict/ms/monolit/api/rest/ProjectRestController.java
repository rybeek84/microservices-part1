package com.ict.ms.monolit.api.rest;

import com.ict.ms.monolit.api.rest.dto.NewProjectDto;
import com.ict.ms.monolit.domain.*;
import com.ict.ms.monolit.domain.vo.UserEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ProjectRestController {

    private ProjectDomainRepository projectRepository;
    private UserDomainRepository userDomainRepository;

    @Autowired
    public ProjectRestController(ProjectDomainRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping(value="/projects")
    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    @GetMapping(value = "/projects/{id}")
    public Project findOne(@PathVariable("id") Long id){
        return projectRepository.findById(id);
    }

    @PostMapping(value = "/projects")
    public Project createProject(HttpServletRequest request, @RequestBody NewProjectDto newProject){
        Project project = new Project();
        project.setName(newProject.getName());
        project.setOwner(new UserEmail(request.getHeader("userId")));
        project.setUuid(UUID.randomUUID());
        project.addMember(ProjectMember.builder()
                .user(project.getOwner())
                .project(project)
                .allowedCreateTask(true)
                .allowedUpdateTask(true)
                .allowedDeleteTask(true)
                .build());
        return projectRepository.save(project);
    }

    @PutMapping(value = "/projects/{id}")
    public Project updateProject(@RequestBody Project project, @PathVariable("id") Long id ){
        return projectRepository.save(project);
    }

    @PostMapping(value = "/projects/{id}/tasks")
    public Task addTaskToProject(@RequestHeader("userId") String userEmail,
                                 @RequestBody Task task,
                                 @PathVariable("id") Long projectId){
        Project project = projectRepository.findById(projectId);
        task.setUuid(UUID.randomUUID());

        if(project.canCreateTask(userEmail)){
            project.addTask(task);
            projectRepository.save(project);
        }

        return project.findTask(task.getUuid());

    }
}

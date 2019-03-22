package com.ict.ms.monolit.api.rest;

import com.ict.ms.monolit.api.rest.dto.NewProjectDto;
import com.ict.ms.monolit.api.rest.dto.NewTaskDto;
import com.ict.ms.monolit.domain.*;
import com.ict.ms.monolit.domain.exception.InvalidProjectMemberException;
import com.ict.ms.monolit.domain.exception.ProjectNotFoundException;
import com.ict.ms.monolit.domain.vo.UserEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class ProjectRestController {

    private ProjectDomainRepository projectRepository;

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
        return projectRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException(id));
    }

    @DeleteMapping(value = "/projects/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity delete(@PathVariable("id") Long id){
         return projectRepository.findById(id)
                 .map(project -> {
                     projectRepository.delete(id);
                     return ResponseEntity.noContent().build();
                 })
                 .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping(value = "/projects")
    @ResponseStatus(HttpStatus.CREATED)
    public Project createProject(HttpServletRequest request, @RequestBody NewProjectDto newProject){
        Project project = Project.builder()
                .name(newProject.getName())
                .owner(new UserEmail(request.getHeader("userId")))
                .build();


        return projectRepository.save(project);
    }

    @PostMapping(value = "/projects/{id}/tasks")
    public Task addTaskToProject(@RequestHeader("userId") String userEmail,
                                 @RequestBody NewTaskDto newTask,
                                 @PathVariable("id") Long projectId){
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() ->new ProjectNotFoundException(projectId));

        UserEmail author = new UserEmail(userEmail);

        if(project.isMember(author)){

            Task task = Task.builder()
                    .author(author)
                    .name(newTask.getName())
                    .description(newTask.getDescription())
                    .assignedTo(newTask.getAssignedTo())
                    .dueDate(newTask.getDueDate())
                    .build();
            project.addTask(task);
            projectRepository.save(project);
            return project.findTask(task.getUuid());
        }

        throw new InvalidProjectMemberException(userEmail);

    }
}

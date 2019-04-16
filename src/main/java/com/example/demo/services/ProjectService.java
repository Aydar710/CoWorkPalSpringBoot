package com.example.demo.services;

import com.example.demo.models.Project;
import com.example.demo.repositories.project.ProjectRepository;
import com.example.demo.repositories.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UsersRepository usersRepository;

    public List<Project> getAllAdminsProjects(Long userId){
        List<Project> projects =  projectRepository.findAllByMainAdmin_Id(userId);
        List<Project> adminsProjects = projectRepository.findAllProjectsWhereUserIsAdmin(userId);
        adminsProjects.addAll(projects);
        return adminsProjects;
    }

    public void addProject(Project project){
        usersRepository.changeRoleToAdmin(project.getMainAdmin().getId());
        projectRepository.save(project);
    }
}

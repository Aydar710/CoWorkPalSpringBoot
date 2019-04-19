package com.example.demo.controllers;

import com.example.demo.Helper;
import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repositories.project.ProjectRepository;
import com.example.demo.repositories.task.TaskRepository;
import com.example.demo.repositories.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AdminsProjectInfoController {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projectReposiory;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/projectInfoTasks")
    String getProjectInfoTasksPage(HttpServletRequest request, ModelMap modelMap) {

        int projectId = Helper.getProjectIdFromCookie(request);
        ArrayList<Task> allTasks = (ArrayList<Task>) taskRepository.getAllByProjectIdAndIsDone((long) projectId, false);
        modelMap.addAttribute("tasks", allTasks);
        Optional<Project> project = projectReposiory.findById((long) projectId);
        if (project.isPresent()){
            modelMap.addAttribute("projectName", project.get().getName());
        }

        int userId = Helper.getUserIdFromCookie(request);
        User currentUser = usersRepository.findById((long) userId).get();
        //String role = currentUser.getRole().name();
        //TODO получить роль из текущего пользователя
        String role = "Admin";
        modelMap.addAttribute("role", role);
        return "project_infoTasks";
    }

    @PostMapping(value = "addTaskToProject")
    String addTaskToProject(@RequestParam("task") String taskText, HttpServletRequest request){
        int projectid = Helper.getProjectIdFromCookie(request);

        Project project = Project.builder()
                .id((long) projectid)
                .build();

        Task task = Task.builder()
                .task(taskText)
                .project(project)
                .build();

        taskRepository.save(task);
        return "redirect:/projectInfoTasks";
    }
}

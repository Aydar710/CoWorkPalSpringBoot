package com.example.demo.controllers.task;

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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ActiveTasksController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/tasks")
    public String getTasksPage(HttpServletRequest request, ModelMap modelMap) {
        int projectId = Helper.getProjectIdFromCookie(request);
        List<Task> notActiveTasks = taskRepository.getAllByProjectIdAndIsDone((long) projectId, false);
        modelMap.addAttribute("tasks", notActiveTasks);

        Project project = projectRepository.findById((long) projectId).get();
        modelMap.addAttribute("projectName", project.getName());

        User currentUser = usersRepository.findById((long) Helper.getUserIdFromCookie(request)).get();
        modelMap.addAttribute("role", currentUser.getRole());
        return "project_infoTasks";
    }
}
package com.example.demo.controllers.project;

import com.example.demo.models.Project;
import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repositories.project.ProjectRepository;
import com.example.demo.repositories.task.TaskRepository;
import com.example.demo.repositories.user.UsersRepository;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.services.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class StartProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/startProject")
    public String getStartProjectPage() {
        return "start_project";
    }

    @PostMapping("/startProject")
    public String startProject(@RequestParam(value = "projectName", required = false) String projectName,
                               HttpServletRequest request,
                               ModelMap modelMap,
                               Authentication authentication) {

        /*Cookie[] cookies = request.getCookies();
        int userId = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                userId = Integer.parseInt(cookie.getValue());
            }
        }
        User mainAdmin = User.builder()
                .id((long) userId)
                .build();*/

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User mainAdmin = userDetails.getUser();
        Long userId = mainAdmin.getId();
        Project project = Project.builder()
                .name(projectName)
                .mainAdmin(mainAdmin)
                .build();

        projectService.addProject(project);

        modelMap.addAttribute("name", projectName);

        //Редирект на страничку проекта
        Project addedProject = projectRepository.findByNameAndMainAdmin_Id(project.getName(), mainAdmin.getId());
        List<Task> allTasks = taskRepository.findAllByProject_Id(project.getId());
        modelMap.addAttribute("tasks", allTasks);
        modelMap.addAttribute("projectName", addedProject.getName());

        //userId = Helper.getUserIdFromCookie(request);
        Optional<User> currentUser = usersRepository.findById((long) userId);

        modelMap.addAttribute("role", "Admin");

        return "project_infoTasks";
    }
}

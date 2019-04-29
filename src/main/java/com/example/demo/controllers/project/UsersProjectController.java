package com.example.demo.controllers.project;

import com.example.demo.models.Project;
import com.example.demo.models.User;
import com.example.demo.repositories.project.ProjectRepository;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.services.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    ProjectService projectService;

    @GetMapping("/usersProjects")
    public String getUsersPage(ModelMap modelMap, HttpServletRequest request, Authentication authentication) {
        /*Cookie[] cookies = request.getCookies();
        int userId = 0;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("userId")) {
                userId = Integer.parseInt(cookie.getValue());
            }
        }*/
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        Long longUserId = user.getId();
        List<Project> projects = projectService.getAllAdminsProjects(longUserId);
        modelMap.addAttribute("projects", projects);
        return "users_page";
    }
}

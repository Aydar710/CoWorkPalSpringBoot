package com.example.demo.controllers.project;

import com.example.demo.Helper;
import com.example.demo.models.Project;
import com.example.demo.models.User;
import com.example.demo.repositories.project.ProjectRepository;
import com.example.demo.repositories.user.UsersRepository;
import com.example.demo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MembersController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/members")
    public String getMembersPage(HttpServletRequest request, ModelMap modelMap, Authentication authentication) {
        int projectId = Helper.getProjectIdFromCookie(request);
        Project project = projectRepository.findById((long) projectId).get();
//        List<User> projectMembers = projectRepository.getAllProjectMembers((long) projectId);
        List<User> projectMembers = project.getProjectUsers();

        modelMap.addAttribute("members", projectMembers);
//        int userId = Helper.getUserIdFromCookie(request);
//        User user = usersRepository.findById((long) userId).get();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        String userRole = user.getRole().name();
        modelMap.addAttribute("role", userRole);
        return "members";
    }
}

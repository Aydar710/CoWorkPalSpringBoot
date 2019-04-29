package com.example.demo.controllers;

import com.example.demo.Helper;
import com.example.demo.models.User;
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

@Controller
public class AdminListController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/admins")
    public String getAdminsPage(HttpServletRequest request, ModelMap modelMap, Authentication authentication) {
        int projectId = Helper.getProjectIdFromCookie(request);
        List<User> projectAdmins = projectService.getAllAdmins((long) projectId);
        modelMap.addAttribute("admins", projectAdmins);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        modelMap.addAttribute("role", user.getRole().name());
        return "admins";
    }

    @PostMapping("/addAdmin")
    public String addAdminToProject(@RequestParam("email") String email, HttpServletRequest request) {
        int projectId = Helper.getProjectIdFromCookie(request);
        User admin = usersRepository.findByEmail(email);
        if (admin != null) {
            projectService.addAdminToProject(admin.getId(), (long) projectId);
        }
        return "redirect:/admins";
    }
}

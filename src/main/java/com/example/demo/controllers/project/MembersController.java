package com.example.demo.controllers.project;

import com.example.demo.Helper;
import com.example.demo.models.User;
import com.example.demo.repositories.project.ProjectRepository;
import com.example.demo.repositories.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String getMembersPage(HttpServletRequest request, ModelMap modelMap) {
        int projectId = Helper.getProjectIdFromCookie(request);
        List<User> projectMembers = projectRepository.getAllProjectMembers((long) projectId);
        User testUser = User.builder()
                .id((long) 4)
                .name("UserName")
                .email("useremail")
                .build();
        projectMembers.add(testUser);
        modelMap.addAttribute("members", projectMembers);
        int userId = Helper.getUserIdFromCookie(request);
        User user = usersRepository.findById((long) userId).get();
        modelMap.addAttribute("role", user.getRole().name());
        return "members";
    }
}

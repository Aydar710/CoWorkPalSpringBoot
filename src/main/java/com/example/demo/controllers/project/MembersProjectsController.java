package com.example.demo.controllers.project;

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
public class MembersProjectsController {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/membersProjects")
    public String getMemberProjectsPage(HttpServletRequest request, ModelMap modelMap, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User member = userDetails.getUser();
        List<Project> membersProjects = member.getProjects();
        //List<Project> membersProjects = (ArrayList<Project>) projectRepository.getAllMembersProjects(memberId);
        modelMap.addAttribute("membersProjects", membersProjects);
        //request.getRequestDispatcher("jsp/membersProjects.jsp").forward(request, response);
        return "membersProjects";
    }
}

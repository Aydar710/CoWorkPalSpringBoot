package com.example.demo.controllers;

import com.example.demo.Helper;
import com.example.demo.models.Invite;
import com.example.demo.models.Project;
import com.example.demo.models.User;
import com.example.demo.repositories.invite.InviteRepository;
import com.example.demo.repositories.project.ProjectRepository;
import com.example.demo.repositories.user.UsersRepository;
import com.example.demo.services.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddMemberController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private InviteRepository inviteRepository;


    @GetMapping("/addMember")
    public String getAddMemberPage() {
        return "addMember";
    }

    @PostMapping("/addMember")
    public void addMember(@RequestParam("usersEmail") String usersEmail, HttpServletRequest request) {
        User user = usersRepository.findByEmail(usersEmail);
        int projectId = Helper.getProjectIdFromCookie(request);
        Project project = projectRepository.findById((long) projectId).get();

        Invite invite = Invite.builder()
                .project(project)
                .user(user)
                .build();

        inviteRepository.save(invite);
    }
}

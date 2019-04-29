package com.example.demo.controllers;

import com.example.demo.models.Invite;
import com.example.demo.repositories.invite.InviteRepository;
import com.example.demo.repositories.project.ProjectRepository;
import com.example.demo.repositories.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AcceptInviteController {

    @Autowired
    InviteRepository inviteRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/accept")
    public ResponseEntity acceptInvite(@RequestParam("inviteId") Long inviteId){
        Invite invite = inviteRepository.findById(inviteId).get();
        projectRepository.addMemberToProject(invite.getUser().getId(), invite.getProject().getId());
        inviteRepository.delete(invite);
        return ResponseEntity.status(200).build();
    }
}

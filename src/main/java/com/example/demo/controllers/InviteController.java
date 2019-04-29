package com.example.demo.controllers;

import com.example.demo.models.Invite;
import com.example.demo.models.User;
import com.example.demo.repositories.invite.InviteRepository;
import com.example.demo.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class InviteController {

    @Autowired
    InviteRepository inviteRepository;

    @GetMapping("/invite")
    public String getInvitePage(HttpServletRequest request, ModelMap modelMap, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        Long userId = user.getId();
        List<Invite> usersInvites = inviteRepository.getAllUserInvites((long) userId);
        modelMap.addAttribute("invites", usersInvites);
        return "invite";
    }
}

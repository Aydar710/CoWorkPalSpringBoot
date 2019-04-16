package com.example.demo.controllers.project;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AddProjectIdController {

    @RequestMapping(value = "/addProjectId", method = RequestMethod.POST)
    public void addProjectIdCookie(@RequestParam("projectId") String projectId, HttpServletResponse response) {
        int projectIdInt = Integer.parseInt(projectId);
        Cookie cookie = new Cookie("projectId", String.valueOf(projectIdInt));
        response.addCookie(cookie);
    }
}

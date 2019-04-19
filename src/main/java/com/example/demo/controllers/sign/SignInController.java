package com.example.demo.controllers.sign;

import com.example.demo.forms.SignInForm;
import com.example.demo.models.User;
import com.example.demo.repositories.user.UsersRepository;
import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {

    @Autowired
    UserService userService;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/signIn")
    public String getSignInPage() {
        return "sign/sign_in";
    }

    @PostMapping("/signIn")
    public String signInUser(SignInForm form, HttpServletResponse resp) {
        if (userService.isExistsByEmailAndPassword(form.getEmail(), form.getPassword())) {
            User currentUser = usersRepository.findByEmail(form.getEmail());
            if (!currentUser.isEnabled()) {
                return "redirect:/signIn";
            }
            Cookie cookieUserId = new Cookie("userId", String.valueOf(currentUser.getId()));
            resp.addCookie(cookieUserId);
            return "redirect:/usersProjects";
        } else {
            return "redirect:/signIn";
        }
    }


}

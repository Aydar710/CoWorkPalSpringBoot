package com.example.demo.controllers.sign;

import com.example.demo.forms.SignUpForm;
import com.example.demo.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {

    @Autowired
    private UserService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign/sign_up";
    }

    @PostMapping("/signUp")
    public String signUpUser(SignUpForm signUpForm) {
        signUpService.signUp(signUpForm);
        return "redirect:/signIn";
    }
}

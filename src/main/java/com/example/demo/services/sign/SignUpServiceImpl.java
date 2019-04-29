package com.example.demo.services.sign;

import com.example.demo.forms.SignUpForm;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.models.UserState;
import com.example.demo.repositories.user.UsersRepository;
import com.example.demo.services.confirm.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public void signUp(SignUpForm form) {
        String confirmString = UUID.randomUUID().toString();

        User user = User.builder()
                .passwordHash(passwordEncoder.encode(form.getPassword()))
                .email(form.getEmail())
                .name(form.getName())
                .role(Role.User)
                .state(UserState.NOT_CONFIRMED)
                .confirmString(confirmString)
                .build();

        usersRepository.save(user);
        String text = "Для подтверждения регистрации <a href='http://localhost:8080/confirm/" + user.getConfirmString() + "'>" +"Пройдите по ссылке" + "</a>";
        System.out.println(text);
        emailService.sendMail("Подтвреждение регистрации", text, user.getEmail());
    }
}

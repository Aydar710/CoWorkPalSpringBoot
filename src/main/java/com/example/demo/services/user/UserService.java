package com.example.demo.services.user;

import com.example.demo.forms.SignUpForm;
import com.example.demo.models.User;
import com.example.demo.repositories.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public void signUp(SignUpForm form) {
        User user = User.builder()
                .email(form.getEmail())
                .passwordHash(form.getPassword())
                .name(form.getName())
                .build();

        usersRepository.save(user);
    }

    public boolean isExistsByEmailAndPassword(String email, String password) {
        return usersRepository.existsByEmailAndPasswordHash(email, password);
    }
}

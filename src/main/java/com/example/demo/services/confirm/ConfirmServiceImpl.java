package com.example.demo.services.confirm;

import com.example.demo.models.User;
import com.example.demo.models.UserState;
import com.example.demo.repositories.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void confirm(String userConfirmString) {
        Optional<User> userOptional = usersRepository.findOneByConfirmString(userConfirmString);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setState(UserState.CONFIRMED);
            usersRepository.save(user);
        } else {
            throw new IllegalArgumentException();
        }
    }
}

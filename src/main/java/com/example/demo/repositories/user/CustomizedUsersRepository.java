package com.example.demo.repositories.user;

import com.example.demo.models.User;

import java.util.List;

public interface CustomizedUsersRepository<User> {

    void changeRoleToAdmin(int id);
}

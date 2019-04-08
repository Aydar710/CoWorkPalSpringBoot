package com.example.demo.repositories.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomizedUsersRepositoryImpl<User> implements CustomizedUsersRepository<User> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void changeRoleToAdmin(int id) {
        //entityManager.createQuery("UPDATE users SET is_admin = true where id = ")
    }
}

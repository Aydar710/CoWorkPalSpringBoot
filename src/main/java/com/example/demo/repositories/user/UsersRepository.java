package com.example.demo.repositories.user;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    User findByEmail(String email);

    @Query("update users set is_admin = true where id = :id")
    void changeRoleToAdmin(@Param("id") Long id);
}

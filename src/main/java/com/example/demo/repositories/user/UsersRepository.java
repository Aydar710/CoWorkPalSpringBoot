package com.example.demo.repositories.user;

import com.example.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User save(User user);

    Optional<User> findById(Long id);

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update users set is_admin = true where id = :id", nativeQuery = true)
    int changeRoleToAdmin(@Param("id") Long id);

    boolean existsByEmailAndPasswordHash(String email, String password);

    Optional<User> findOneByConfirmString(String confirmString);
}

package com.example.demo.repositories.invite;

import com.example.demo.models.Invite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InviteRepository extends JpaRepository<Invite, Long> {
    List<Invite> findAll();

    Invite save(Invite invite);

    Optional<Invite> findById(Long id);

    void deleteById(Long id);

    @Query(value = "select * from invite where user_id = :id", nativeQuery = true)
    List<Invite> getAllUserInvites(@Param("id") Long id);
}

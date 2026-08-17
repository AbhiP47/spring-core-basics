package com.abhinav.springsecurity.repository;

import com.abhinav.springsecurity.entity.User;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByUsername(String username);
}

package com.apiuygulama.apiuygulama.repository;

import com.apiuygulama.apiuygulama.model.Exercise;
import com.apiuygulama.apiuygulama.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query("SELECT c FROM User c WHERE c.email = ?1")
    public User findByEmail(String email);

    public User findByResetPasswordToken(String token);

    @Query("SELECT count(id) FROM User")
    public int userCount();

    List<User> findAllByUsername(String name);
}

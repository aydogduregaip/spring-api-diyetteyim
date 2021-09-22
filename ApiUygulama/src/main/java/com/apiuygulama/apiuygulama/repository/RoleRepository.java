package com.apiuygulama.apiuygulama.repository;

import com.apiuygulama.apiuygulama.model.ERole;
import com.apiuygulama.apiuygulama.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

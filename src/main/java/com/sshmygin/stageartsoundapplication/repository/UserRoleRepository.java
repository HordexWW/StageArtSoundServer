package com.sshmygin.stageartsoundapplication.repository;

import com.sshmygin.stageartsoundapplication.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(String name);
}
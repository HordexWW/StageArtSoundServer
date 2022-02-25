package com.sshmygin.stageartsoundapplication.repository;

import com.sshmygin.stageartsoundapplication.model.Status;
import com.sshmygin.stageartsoundapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByStatus(Status status);
    Optional<User> findByUsername(String username);

}

package com.sshmygin.stageartsoundapplication.service.implementation;

import com.sshmygin.stageartsoundapplication.model.Status;
import com.sshmygin.stageartsoundapplication.model.User;
import com.sshmygin.stageartsoundapplication.model.UserRole;
import com.sshmygin.stageartsoundapplication.repository.UserRepository;
import com.sshmygin.stageartsoundapplication.repository.UserRoleRepository;
import com.sshmygin.stageartsoundapplication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User register(User user) {
        UserRole userRole = userRoleRepository.findByName("ROLE_USER");
        List<UserRole> userRoles = new ArrayList<>() {{
            add(userRole);
        }};
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("UserService - register - User has been registered: {}", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> foundUsers= userRepository.findAll();
        log.info("UserService - getAll - users found: {}", foundUsers.size());
        return foundUsers;
    }

    @Override
    public User findByUsername(String username) {
        User foundUser = userRepository
                .findByUsername(username)
                .orElseThrow(IllegalStateException::new);
        log.info("UserService - findByUsername - found user: {}", foundUser);
        return foundUser;
    }

    @Override
    public User findById(Long id) {
        User foundUser = userRepository
                .findById(id)
                .orElseThrow(IllegalStateException::new);
        log.info("UserService - findById - found user: {} with id: {}", foundUser, id);
        return foundUser;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("UserService - delete - user with id: {} has been deleted", id);
    }
}

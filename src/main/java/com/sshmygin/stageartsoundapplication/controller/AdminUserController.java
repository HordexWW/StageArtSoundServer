package com.sshmygin.stageartsoundapplication.controller;

import com.sshmygin.stageartsoundapplication.dto.admin.AdminUserDto;
import com.sshmygin.stageartsoundapplication.service.implementation.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController(value = "/admin")
public class AdminUserController {
    UserServiceImpl userService;

    public AdminUserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserDto>> getAllUsers() {

        List<AdminUserDto> userDtos;
            userDtos = userService.getAll().stream()
                    .map(AdminUserDto::fromUser)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(userDtos);

    }
}

package com.sshmygin.stageartsoundapplication.dto.auth;

import com.sshmygin.stageartsoundapplication.model.User;
import com.sshmygin.stageartsoundapplication.model.UserRole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class AuthenticationResponseDto {
    private final String username;
    private final String firstName;
    private final String lastName;
    private String token;
    private final String email;
    private final List<String> roles;

    public AuthenticationResponseDto(User user) {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream()
                .map(UserRole::getName)
                .collect(Collectors.toList());
    }

    public void setToken(String token) {
        this.token = token;
    }
}

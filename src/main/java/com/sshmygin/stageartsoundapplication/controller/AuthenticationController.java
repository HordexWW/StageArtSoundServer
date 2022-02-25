package com.sshmygin.stageartsoundapplication.controller;


import com.sshmygin.stageartsoundapplication.dto.auth.AuthenticationRequestDto;
import com.sshmygin.stageartsoundapplication.dto.auth.AuthenticationResponseDto;
import com.sshmygin.stageartsoundapplication.model.User;
import com.sshmygin.stageartsoundapplication.security.jwt.JwtTokenProvider;
import com.sshmygin.stageartsoundapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto authRequestDto) {

        String username = authRequestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authRequestDto.getPassword()));
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        String token = jwtTokenProvider.createToken(username, user.getRoles());

        AuthenticationResponseDto responce = new AuthenticationResponseDto(user);
        responce.setToken(token);

        return ResponseEntity.ok(responce);
    }

}

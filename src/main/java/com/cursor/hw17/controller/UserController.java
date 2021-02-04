package com.cursor.hw17.controller;

import com.cursor.hw17.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDetails> createAuthenticationToken(@RequestBody AuthenticationRequest auth) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));
        var user = userService.login(auth.getUsername(), auth.getPassword());
        return ResponseEntity.ok(user);
    }

    @RequestMapping(value = "/dummy", method = RequestMethod.POST)
    public ResponseEntity<String> createAuthenticationToken() {
        return ResponseEntity.ok("user");

    }

    @Data
    public static class AuthenticationRequest implements Serializable {
        private String username;
        private String password;
    }
}


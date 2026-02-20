package com.eyebeem.backend.controller;

import com.eyebeem.backend.dto.UserDto;
import com.eyebeem.backend.service.AuthService;
import com.eyebeem.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    /** Login or register: POST body { "name": "...", "email": "..." }. Returns user with order history. */
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequest request) {
        UserDto user = authService.loginOrRegister(request.getName(), request.getEmail());
        return ResponseEntity.ok(user);
    }

    /** Get user profile and order history by user id. */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserWithOrderHistory(userId));
    }

    public static class LoginRequest {
        private String name;
        private String email;
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }
}

package com.eyebeem.backend.service;

import com.eyebeem.backend.dto.UserDto;
import com.eyebeem.backend.entity.User;
import com.eyebeem.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserService userService;

    public AuthService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /** Find user by email or create new one. Returns user with order history. */
    @Transactional
    public UserDto loginOrRegister(String name, String email) {
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User u = new User();
                    u.setName(name);
                    u.setEmail(email);
                    return userRepository.save(u);
                });
        return userService.getUserWithOrderHistory(user.getId());
    }
}

package com.example.Ecommerce.Ecommerce.controller;

import com.example.Ecommerce.Ecommerce.dto.LoginRequest;
import com.example.Ecommerce.Ecommerce.dto.LoginResponse;
import com.example.Ecommerce.Ecommerce.dto.UserDto;
import com.example.Ecommerce.Ecommerce.dto.UserRoleUpdateRequest;
import com.example.Ecommerce.Ecommerce.exception.AccessDeniedCustomException;
import com.example.Ecommerce.Ecommerce.helper.CheckCurrentUserRole;
import com.example.Ecommerce.Ecommerce.helper.JwtUtil;
import com.example.Ecommerce.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.Ecommerce.services.UserServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-operations")
public class User {

    @Autowired
    private UserServiceOperations userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private  JwtUtil jwtUtil;

    @Autowired
    private CheckCurrentUserRole roleChecker;

    @PostMapping("/create-new-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(">>> Current User: " + authentication.getName());
        System.out.println(">>> Authorities: " + authentication.getAuthorities());
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        com.example.Ecommerce.Ecommerce.entity.User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());
        LoginResponse response = new LoginResponse(token, user.getEmail(), user.getRole().name());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update-user-role")
    public ResponseEntity<UserDto> updateUserRole(@RequestBody UserRoleUpdateRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isSuperAdmin = auth.getAuthorities()
                .stream().anyMatch(a -> a.getAuthority().equals("ROLE_SUPER_ADMIN"));

        if (!isSuperAdmin) {
            throw new AccessDeniedCustomException("Required role: SUPER_ADMIN");
        }

        UserDto updatedUser = userService.updateUserRole(request.getUserId(), request.getRole());
        return ResponseEntity.ok(updatedUser);
    }







}

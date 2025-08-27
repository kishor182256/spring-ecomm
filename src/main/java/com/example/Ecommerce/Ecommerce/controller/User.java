package com.example.Ecommerce.Ecommerce.controller;

import com.example.Ecommerce.Ecommerce.dto.UserDto;
import com.example.Ecommerce.Ecommerce.services.UserServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-operations")
public class User {

    @Autowired
    private UserServiceOperations userService;

    @PostMapping("/create-new-user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public String login() {
        return "User logged in SuccesFully";
    }


}

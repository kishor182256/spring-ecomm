package com.example.Ecommerce.Ecommerce.services.impl;

import com.example.Ecommerce.Ecommerce.dto.UserDto;
import com.example.Ecommerce.Ecommerce.entity.User;
import com.example.Ecommerce.Ecommerce.exception.UserCreationException;
import com.example.Ecommerce.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.Ecommerce.services.UserServiceOperations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServiceOperations {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userData) {
        if (userRepository.existsByEmail(userData.getEmail())) {
            throw new UserCreationException(
                    String.format("A user with %s emailId already exists.", userData.getEmail()));
        }

        try {
            String encodedPassword = passwordEncoder.encode(userData.getPassword());
            User user = modelMapper.map(userData, User.class);
            user.setPassword(encodedPassword);
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        } catch (Exception e) {
            throw new UserCreationException("Failed to create user: " + e.getMessage(), e);
        }
    }


    @Override
    public UserDto updateUser(UserDto userData, String userId) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public UserDto getAllUser() {
        return null;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return null;
    }
}

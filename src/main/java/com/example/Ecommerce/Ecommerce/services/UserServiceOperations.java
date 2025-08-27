package com.example.Ecommerce.Ecommerce.services;


import com.example.Ecommerce.Ecommerce.dto.UserDto;

public interface UserServiceOperations {

    UserDto createUser(UserDto userData);

    UserDto updateUser(UserDto userData,String userId);

    void deleteUser(String userId);

    UserDto getAllUser();

    UserDto getUserByEmail(String email);

}

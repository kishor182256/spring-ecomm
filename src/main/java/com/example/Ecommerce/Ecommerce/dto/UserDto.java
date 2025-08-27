package com.example.Ecommerce.Ecommerce.dto;

import com.example.Ecommerce.Ecommerce.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;
    private Roles role=Roles.USER;
    private String password;
}

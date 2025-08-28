package com.example.Ecommerce.Ecommerce.dto;


import com.example.Ecommerce.Ecommerce.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;
    private String email;
    private String role;
    private String refreshToken;


}

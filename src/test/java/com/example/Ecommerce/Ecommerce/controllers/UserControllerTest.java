package com.example.Ecommerce.Ecommerce.controllers;

import com.example.Ecommerce.Ecommerce.controller.User;
import com.example.Ecommerce.Ecommerce.dto.UserDto;
import com.example.Ecommerce.Ecommerce.helper.CheckCurrentUserRole;
import com.example.Ecommerce.Ecommerce.helper.JwtUtil;
import com.example.Ecommerce.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.Ecommerce.services.UserServiceOperations;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(User.class)   // testing only the User controller
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceOperations userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private CheckCurrentUserRole roleChecker;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreateUser_Success() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("kishor");
        userDto.setEmail("kishor@example.com");
        userDto.setPassword("encodedPassword");

        Mockito.when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

        String requestBody = """
                {
                  "id": 1,
                  "username": "kishor",
                  "email": "kishor@example.com",
                  "password": "plainPassword"
                }
                """;

        mockMvc.perform(post("/api/user-operations/create-new-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf())
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("kishor@example.com"))
                .andExpect(jsonPath("$.username").value("kishor"));
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testCreateUser_ForbiddenForNonAdmin() throws Exception {
        String requestBody = """
                {
                  "id": 1,
                  "username": "kishor",
                  "email": "kishor@example.com",
                  "password": "plainPassword"
                }
                """;

        mockMvc.perform(post("/api/user-operations/create-new-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isForbidden());
    }
}

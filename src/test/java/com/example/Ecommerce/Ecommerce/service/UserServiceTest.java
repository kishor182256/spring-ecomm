package com.example.Ecommerce.Ecommerce.service;

import com.example.Ecommerce.Ecommerce.dto.UserDto;
import com.example.Ecommerce.Ecommerce.entity.User;
import com.example.Ecommerce.Ecommerce.exception.UserCreationException;
import com.example.Ecommerce.Ecommerce.repository.UserRepository;
import com.example.Ecommerce.Ecommerce.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("kishor");
        userDto.setEmail("kishor@example.com");
        userDto.setPassword("plainPassword");

        user = new User();
        user.setId(1L);
        user.setUsername("kishor");
        user.setEmail("kishor@example.com");
        user.setPassword("encodedPassword");
    }

    @Test
    void testCreateUser_Success() {
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(modelMapper.map(userDto, User.class)).thenReturn(new User());
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto result = userService.createUser(userDto);

        assertNotNull(result);
        assertEquals("kishor@example.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);

        UserCreationException exception = assertThrows(UserCreationException.class,
                () -> userService.createUser(userDto));

        assertTrue(exception.getMessage().contains("already exists"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testCreateUser_ExceptionDuringSave() {
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encodedPassword");
        when(modelMapper.map(userDto, User.class)).thenReturn(new User());
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("DB failure"));

        UserCreationException exception = assertThrows(UserCreationException.class,
                () -> userService.createUser(userDto));

        assertTrue(exception.getMessage().contains("Failed to create user"));
    }
}

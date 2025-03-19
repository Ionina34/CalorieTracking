package org.example.calorieTracker.web.controller;

import org.example.calorieTracker.model.User;
import org.example.calorieTracker.service.UserService;
import org.example.calorieTracker.web.dto.request.CreateUserRequest;
import org.example.calorieTracker.web.dto.response.UserResponse;
import org.example.calorieTracker.web.mapper.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private User user;
    private UserResponse userResponse;
    private CreateUserRequest createUserRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1L);
        user.setName("Test User");

        userResponse = new UserResponse();
        userResponse.setId(1L);
        userResponse.setName("Test User");

        createUserRequest = new CreateUserRequest();
        createUserRequest.setName("Test User");
    }

    @Test
    public void testGetUserById() {
        when(userService.findById(1L)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
        verify(userService).findById(1L);
        verify(userMapper).toDto(user);
    }

    @Test
    public void testCreateUser() {
        when(userMapper.toEntity(createUserRequest)).thenReturn(user);
        when(userService.addUser(user)).thenReturn(user);
        when(userMapper.toDto(user)).thenReturn(userResponse);

        ResponseEntity<UserResponse> response = userController.createUser(createUserRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
        verify(userMapper).toEntity(createUserRequest);
        verify(userService).addUser(user);
        verify(userMapper).toDto(user);
    }
}

package org.example.calorieTracker.web.controller;

import jakarta.validation.Valid;
import org.example.calorieTracker.model.User;
import org.example.calorieTracker.web.dto.request.CreateUserRequest;
import org.example.calorieTracker.web.dto.response.UserResponse;
import org.example.calorieTracker.service.UserService;
import org.example.calorieTracker.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        User user = userService.findById(id);

        return ResponseEntity.ok(
                userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid CreateUserRequest request){
        User user = userService.addUser(
                userMapper.toEntity(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toDto(user));
    }
}

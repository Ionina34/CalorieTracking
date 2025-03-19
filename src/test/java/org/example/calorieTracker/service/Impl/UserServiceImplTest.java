package org.example.calorieTracker.service.Impl;

import org.example.calorieTracker.exception.EntityNotFoundException;
import org.example.calorieTracker.model.Gender;
import org.example.calorieTracker.model.GoalType;
import org.example.calorieTracker.model.User;
import org.example.calorieTracker.repository.UserRepository;
import org.example.calorieTracker.service.AbstractTest;
import org.example.calorieTracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceImplTest extends AbstractTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
    }

    @Test
    public void testAddUser_Success_thanReturnNewUser() {
        int record = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users;",
                Integer.class
        );
        assertEquals(record, 0);

        User user = new User();
        user.setAge(20);
        user.setName("name");
        user.setEmail("email");
        user.setGoal(GoalType.WEIGHT_LOSS);
        user.setGender(Gender.FEMALE);

        User savedUser = userService.addUser(user);

        assertNotNull(savedUser);

        record = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM users;",
                Integer.class
        );

        assertEquals(record, 1);
        assertEquals(savedUser.getName(), user.getName());
        assertEquals(savedUser.getEmail(), user.getEmail() );
        assertEquals(savedUser.getAge(), user.getAge() );
    }

    @Test
    public void testFindById_Success_thanReturnUser() {
        User user = new User();
        user.setAge(20);
        user.setName("name");
        user.setEmail("email");
        user.setGoal(GoalType.WEIGHT_LOSS);
        user.setGender(Gender.FEMALE);
        User savedUser = userService.addUser(user);

        User foundUser = userService.findById(savedUser.getId());

        assertNotNull(foundUser);

        String actualName = jdbcTemplate.queryForObject(
                "SELECT name FROM users WHERE id = " + savedUser.getId(),
                String.class
        );

        assertEquals(actualName, user.getName());
    }

    @Test
    public void testFindById_userNotFound_thanReturnException() {
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> userService.findById(100L)
        );

        String message="User not found with ID: 100";
        assertEquals(exception.getLocalizedMessage(), message);
    }
}

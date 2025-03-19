package org.example.calorieTracker.service;

import org.example.calorieTracker.model.User;

public interface UserService {
    User addUser(User user);

    User findById(Long id);
}

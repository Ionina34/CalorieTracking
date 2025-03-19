package org.example.calorieTracker.service.Impl;

import org.example.calorieTracker.exception.EntityNotFoundException;
import org.example.calorieTracker.model.User;
import org.example.calorieTracker.repository.UserRepository;
import org.example.calorieTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        user.calculateDailyCalories();
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }
}

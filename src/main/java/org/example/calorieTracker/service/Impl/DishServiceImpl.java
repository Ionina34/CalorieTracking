package org.example.calorieTracker.service.Impl;

import org.example.calorieTracker.exception.EntityNotFoundException;
import org.example.calorieTracker.model.Dish;
import org.example.calorieTracker.repository.DishRepository;
import org.example.calorieTracker.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Dish addDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Dish not found with ID: "+ id));
    }
}

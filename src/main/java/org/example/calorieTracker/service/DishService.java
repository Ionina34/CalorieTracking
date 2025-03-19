package org.example.calorieTracker.service;

import org.example.calorieTracker.model.Dish;

public interface DishService {

    Dish addDish(Dish dish);

    Dish findById(Long id);
}

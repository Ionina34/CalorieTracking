package org.example.calorieTracker.service;

import org.example.calorieTracker.model.Meal;

import java.util.List;

public interface MealService {
    Meal addMeal(Meal meal);

    Meal findMealById(Long id);

    List<Meal> findByUser(Long userId);
}

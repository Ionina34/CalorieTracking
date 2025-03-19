package org.example.calorieTracker.service.Impl;

import org.example.calorieTracker.exception.EntityNotFoundException;
import org.example.calorieTracker.model.Dish;
import org.example.calorieTracker.model.Meal;
import org.example.calorieTracker.model.User;
import org.example.calorieTracker.repository.MealRepository;
import org.example.calorieTracker.service.DishService;
import org.example.calorieTracker.service.MealService;
import org.example.calorieTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private DishService dishService;

    @Override
    public Meal addMeal(Meal meal) {
        User user = userService.findById(meal.getUser().getId());
        meal.setUser(user);
        for (Dish dish : meal.getDishes()) {
            dishService.addDish(dish);
        }
        return mealRepository.save(meal);
    }

    @Override
    public Meal findMealById(Long id) {
        return mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with ID: " + id));
    }

    @Override
    public List<Meal> findByUser(Long userId) {
        User user = userService.findById(userId);
        return mealRepository.findByUser(user);
    }
}

package org.example.calorieTracker.service.Impl;

import org.example.calorieTracker.exception.EntityNotFoundException;
import org.example.calorieTracker.model.Dish;
import org.example.calorieTracker.model.Meal;
import org.example.calorieTracker.repository.MealRepository;
import org.example.calorieTracker.service.AbstractTest;
import org.example.calorieTracker.service.MealService;
import org.example.calorieTracker.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MealServiceImplTest extends AbstractTest {

    @Autowired
    private MealService mealService;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    public void setUp(){
        mealRepository.deleteAll();
    }

    @Test
    public void testAddMeal_Success_thanReturnNewMeal(){
        int record = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM meals;",
                Integer.class
        );
        assertEquals(record, 0);

        Meal meal = new Meal();
        meal.setDishes(List.of(new Dish(), new Dish()));
        meal.setLocalDate(LocalDate.now());

        Meal savedMeal = mealService.addMeal(meal);

        assertNotNull(savedMeal);

        record = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM meals;",
                Integer.class
        );
        assertEquals(record, 1);
    }

    @Test
    public void testFindById_Success_thanReturnMeal() {
        Meal meal = new Meal();
        meal.setDishes(List.of(new Dish(), new Dish()));
        meal.setLocalDate(LocalDate.now());
        Meal savedMeal = mealService.addMeal(meal);

        Meal foundDish = mealService.findMealById(savedMeal.getId());

        assertNotNull(foundDish);
    }

    @Test
    public void testFindById_mealNotFound_thanReturnException() {
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> mealService.findMealById(100L)
        );

        String message="Meal not found with ID: 100";
        assertEquals(exception.getLocalizedMessage(), message);
    }
}

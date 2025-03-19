package org.example.calorieTracker.service.Impl;

import org.example.calorieTracker.exception.EntityNotFoundException;
import org.example.calorieTracker.model.Dish;
import org.example.calorieTracker.repository.DishRepository;
import org.example.calorieTracker.service.AbstractTest;
import org.example.calorieTracker.service.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class DishServiceImplTest extends AbstractTest {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishRepository dishRepository;

    @BeforeEach
    public void setUp() {
        dishRepository.deleteAll();
    }

    @Test
    public void testAddDish_Success_thanReturnNewDish() {
        int record = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM dishes;",
                Integer.class
        );
        assertEquals(record, 0);

        Dish dish = new Dish();
        dish.setName("Pasta");
        dish.setFat(15);
        dish.setProtein(58.1);
        dish.setCarbohydrates(555.6);
        dish.setCaloriesPerServing(85);

        Dish savedDish = dishService.addDish(dish);

        assertNotNull(savedDish);

        record = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM dishes;",
                Integer.class
        );

        assertEquals(record, 1);
        assertEquals(savedDish.getName(), dish.getName());
    }

    @Test
    public void testFindById_Success_thanReturnDish() {
        Dish dish = new Dish();
        dish.setName("Salad");
        dish.setCaloriesPerServing(150);
        Dish savedDish = dishService.addDish(dish);

        Dish foundDish = dishService.findById(savedDish.getId());

        assertNotNull(foundDish);

        String actualName = jdbcTemplate.queryForObject(
                "SELECT name FROM dishes WHERE id = " + savedDish.getId(),
                String.class
        );

        assertEquals(actualName, dish.getName());
    }

    @Test
    public void testFindById_dishNotFound_thanReturnException() {
        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> dishService.findById(100L)
        );

        String message="Dish not found with ID: 100";
        assertEquals(exception.getLocalizedMessage(), message);
    }
}

package org.example.calorieTracker.web.controller;

import jakarta.validation.Valid;
import org.example.calorieTracker.model.Meal;
import org.example.calorieTracker.service.MealService;
import org.example.calorieTracker.web.dto.request.CreateMealRequest;
import org.example.calorieTracker.web.dto.response.MealResponse;
import org.example.calorieTracker.web.mapper.MealMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private MealMapper mealMapper;

    @GetMapping("/{id}")
    public ResponseEntity<MealResponse> getById(@PathVariable Long id){
        Meal meal = mealService.findMealById(id);

        return ResponseEntity.ok(
                mealMapper.toDto(meal)
        );
    }

    @PostMapping("/user/{id}")
    public ResponseEntity<MealResponse> createMeal(@PathVariable Long id,
                                                   @RequestBody @Valid CreateMealRequest request) {
        Meal meal = mealService.addMeal(
                mealMapper.toEntity(id, request)
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        mealMapper.toDto(meal)
                );
    }
}

package org.example.calorieTracker.web.controller;

import jakarta.validation.Valid;
import org.example.calorieTracker.model.Dish;
import org.example.calorieTracker.service.DishService;
import org.example.calorieTracker.web.dto.request.CreateDishRequest;
import org.example.calorieTracker.web.dto.response.DishResponse;
import org.example.calorieTracker.web.mapper.DishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishMapper dishMapper;

    @GetMapping("/{id}")
    public ResponseEntity<DishResponse> getDishById(@PathVariable Long id){
        Dish dish  = dishService.findById(id);

        return ResponseEntity.ok(
                dishMapper.toDto(dish));
    }

    @PostMapping
    public ResponseEntity<DishResponse> createDish(@RequestBody @Valid CreateDishRequest request){
        Dish dish = dishService.addDish(
                dishMapper.toEntity(request)
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(dishMapper.toDto(dish));
    }
}

package org.example.calorieTracker.web.controller;

import org.example.calorieTracker.model.Dish;
import org.example.calorieTracker.service.DishService;
import org.example.calorieTracker.web.dto.request.CreateDishRequest;
import org.example.calorieTracker.web.dto.response.DishResponse;
import org.example.calorieTracker.web.mapper.DishMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class DishControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DishService dishService;

    @Mock
    private DishMapper dishMapper;

    @InjectMocks
    private DishController dishController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(dishController).build();
    }

    @Test
    public void testGetDishById() throws Exception {
        Dish dish = new Dish();
        DishResponse dishResponse = new DishResponse();
        when(dishService.findById(anyLong())).thenReturn(dish);
        when(dishMapper.toDto(dish)).thenReturn(dishResponse);

        mockMvc.perform(get("/api/v1/dishes/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testCreateDish() throws Exception {
        CreateDishRequest request = new CreateDishRequest();
        request.setProtein(150);
        request.setName("Salad");
        request.setCarbohydrates(150);
        request.setCaloriesPerServing(150);
        request.setFat(150);

        Dish dish = new Dish();
        dish.setProtein(150);
        dish.setName("Salad");
        dish.setCarbohydrates(150);
        dish.setCaloriesPerServing(150);
        dish.setFat(150);

        DishResponse dishResponse = new DishResponse();
        dishResponse.setProtein(150);
        dishResponse.setName("Salad");
        dishResponse.setCarbohydrates(150);
        dishResponse.setCaloriesPerServing(150);
        dishResponse.setFat(150);
        dishResponse.setId(1L);

        when(dishMapper.toEntity(request)).thenReturn(dish);
        when(dishService.addDish(dish)).thenReturn(dish);
        when(dishMapper.toDto(dish)).thenReturn(dishResponse);

        mockMvc.perform(post("/api/v1/dishes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Salad\", \"caloriesPerServing\": 150, " +
                                "\"protein\": 150, \"fat\": 150, " +
                                "\"carbohydrates\": 150}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }
}

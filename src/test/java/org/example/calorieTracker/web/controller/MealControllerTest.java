package org.example.calorieTracker.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.calorieTracker.model.Meal;
import org.example.calorieTracker.service.MealService;
import org.example.calorieTracker.web.dto.request.CreateMealRequest;
import org.example.calorieTracker.web.dto.response.MealResponse;
import org.example.calorieTracker.web.mapper.MealMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MealControllerTest {

    @InjectMocks
    private MealController mealController;

    @Mock
    private MealService mealService;

    @Mock
    private MealMapper mealMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mealController).build();
    }

    @Test
    void testGetById() throws Exception {
        Meal meal = new Meal(); // Создайте объект Meal
        MealResponse mealResponse = new MealResponse(); // Создайте объект MealResponse

        when(mealService.findMealById(1L)).thenReturn(meal);
        when(mealMapper.toDto(meal)).thenReturn(mealResponse);

        mockMvc.perform(get("/api/v1/meals/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());

        verify(mealService).findMealById(1L);
        verify(mealMapper).toDto(meal);
    }

    @Test
    void testCreateMeal() throws Exception {
        Long userId = 1L;
        CreateMealRequest createMealRequest = new CreateMealRequest(); // Заполните данными
        Meal meal = new Meal(); // Создайте объект Meal
        MealResponse mealResponse = new MealResponse(); // Создайте объект MealResponse

        when(mealMapper.toEntity(userId, createMealRequest)).thenReturn(meal);
        when(mealService.addMeal(meal)).thenReturn(meal);
        when(mealMapper.toDto(meal)).thenReturn(mealResponse);

        mockMvc.perform(post("/api/v1/meals/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createMealRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").exists());

        verify(mealMapper).toEntity(userId, createMealRequest);
        verify(mealService).addMeal(meal);
        verify(mealMapper).toDto(meal);
    }
}

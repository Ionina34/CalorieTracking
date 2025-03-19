package org.example.calorieTracker.web.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDishRequest {
    @NotNull(message = "The field must be filled in")
    private String name;

    @Min(value = 1,
    message = "The value must be greater than 1")
    private int caloriesPerServing;

    @Min(value = 0,
    message = "The value must be greater than 0")
    private double protein;

    @Min(value = 0,
    message = "The value must be greater than 0")
    private double fat;

    @Min(value = 0,
    message = "The value must be greater than 0")
    private double carbohydrates;
}

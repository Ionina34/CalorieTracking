package org.example.calorieTracker.web.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;
import org.example.calorieTracker.model.Gender;
import org.example.calorieTracker.model.GoalType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "The field must be filled in")
    private String name;

    @Email
    @NotBlank(message = "The field must be filled in")
    private String email;

    @Min(value = 1,
    message = "The value must be greater than 1")
    @Max(value = 120,
    message = "The value must be less than 120")
    private int age;

    @Min(value = 30,
    message = "The value must be greater than 30")
    private double weight;

    @Min(value = 50,
    message = "The value must be greater than 50")
    private double height;

    @NotNull(message = "The field cannot be empty")
    private GoalType goal;

    @NotNull(message = "The field cannot be empty")
    private Gender gender;
}

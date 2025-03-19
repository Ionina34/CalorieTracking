package org.example.calorieTracker.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMealRequest {

    @NotNull(message = "The field must be filled in")
    private LocalDate localDate;

    @NotNull(message = "The field must be filled in")
    private List<CreateDishRequest> dishes;
}

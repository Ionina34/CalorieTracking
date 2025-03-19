package org.example.calorieTracker.web.dto.response;

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
public class MealResponse {
    private Long id;
    private Long userId;
    private LocalDate localDate;
    private List<DishResponse> dishes;
}

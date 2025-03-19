package org.example.calorieTracker.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DishResponse {
    private Long id;
    private String name;
    private int caloriesPerServing;
    private double protein;
    private double fat;
    private double carbohydrates;
}

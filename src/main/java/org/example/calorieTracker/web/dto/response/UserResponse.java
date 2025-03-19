package org.example.calorieTracker.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.calorieTracker.model.Gender;
import org.example.calorieTracker.model.GoalType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private int age;
    private double weight;
    private double height;
    private GoalType goal;
    private Gender gender;
    private double dailyCaloricIntake;

}

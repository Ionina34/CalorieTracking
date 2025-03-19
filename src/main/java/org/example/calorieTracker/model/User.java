package org.example.calorieTracker.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private int age;

    private double weight;

    private double height;

    @Enumerated(EnumType.STRING)
    private GoalType goal;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "daily_caloric_intake")
    private double dailyCaloricIntake;

    public void calculateDailyCalories() {
        double brm;

        if (gender.equals(Gender.MALE)) {
            brm = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            brm = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        dailyCaloricIntake = brm;
    }
}

package org.example.calorieTracker.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "meals")
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDate localDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "meal_dishes",
    joinColumns = @JoinColumn(name = "meal_id"),
    inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Dish> dishes;
}

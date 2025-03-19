package org.example.calorieTracker.repository;

import org.example.calorieTracker.model.Meal;
import org.example.calorieTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository  extends JpaRepository<Meal, Long> {
    List<Meal> findByUser(User user);
}

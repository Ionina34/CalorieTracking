package org.example.calorieTracker.repository;

import org.example.calorieTracker.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository  extends JpaRepository<Dish,Long> {
}

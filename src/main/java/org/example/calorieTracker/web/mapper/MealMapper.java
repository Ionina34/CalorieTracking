package org.example.calorieTracker.web.mapper;

import org.example.calorieTracker.model.Meal;
import org.example.calorieTracker.web.dto.request.CreateMealRequest;
import org.example.calorieTracker.web.dto.response.ListMealResponse;
import org.example.calorieTracker.web.dto.response.MealResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = DishMapper.class)
public interface MealMapper {

    @Mapping(source = "userId", target = "user.id")
    Meal toEntity(Long userId, CreateMealRequest request);

    @Mapping(source = "meal.user.id", target = "userId")
    MealResponse toDto(Meal meal);

    default ListMealResponse toResponseList(List<Meal> meals) {
        ListMealResponse response = new ListMealResponse();

        response.setMealList(
                meals.stream()
                        .map(this::toDto)
                        .toList()
        );
        return response;
    }
}

package org.example.calorieTracker.web.mapper;

import org.example.calorieTracker.model.Dish;
import org.example.calorieTracker.web.dto.request.CreateDishRequest;
import org.example.calorieTracker.web.dto.response.DishResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishMapper {

    Dish toEntity(CreateDishRequest request);
    DishResponse toDto(Dish dish);

    default List<Dish> requestListToEntityList(List<CreateDishRequest> responses){
        return responses.stream()
                .map(this::toEntity)
                .toList();
    }

    default List<DishResponse> entityListToResponseList(List<Dish> dishes){
        return dishes.stream()
                .map(this::toDto)
                .toList();
    }
}

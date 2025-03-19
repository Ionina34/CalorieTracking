package org.example.calorieTracker.web.mapper;

import org.example.calorieTracker.web.dto.request.CreateUserRequest;
import org.example.calorieTracker.web.dto.response.UserResponse;
import org.example.calorieTracker.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toEntity(CreateUserRequest request);

    UserResponse toDto(User user);
}

package com.example.nutris.food;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface FoodMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    void updateFoodFromDto(FoodDTO dto, @MappingTarget Food entity);
}

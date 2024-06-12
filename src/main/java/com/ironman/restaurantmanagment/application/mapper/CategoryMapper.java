package com.ironman.restaurantmanagment.application.mapper;

import com.ironman.restaurantmanagment.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

// MapStruct Annotation
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {

    //    @Mapping(source = "category", target= "category")
    // Si los 2 nombres son iguales no es necesario poner el source y target
    CategoryDto toDto(Category category);

    CategorySmallDto toSmallDto(Category category);

    CategorySavedDto toSavedDto(Category category);

    Category toEntity(CategoryBodyDto categoryBodyDto);

    Category updateEntity(@MappingTarget Category category, CategoryBodyDto categoryBodyDto);

}

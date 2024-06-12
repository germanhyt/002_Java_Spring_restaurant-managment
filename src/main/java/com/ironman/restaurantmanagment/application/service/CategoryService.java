package com.ironman.restaurantmanagment.application.service;

import com.ironman.restaurantmanagment.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.persistence.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategorySmallDto> findAll();
    CategoryDto findById(Long id);

//    CategoryDto create(CategoryBodyDto categoryBodyDto);
//    CategoryDto update(Long id, CategoryBodyDto categoryBodyDto);
//    CategoryDto disable(Long id);

    CategorySavedDto create(CategoryBodyDto categoryBodyDto);
    CategorySavedDto update(Long id, CategoryBodyDto categoryBodyDto);
    CategorySavedDto disable(Long id);

    List<CategorySmallDto> findByState(String state);
}

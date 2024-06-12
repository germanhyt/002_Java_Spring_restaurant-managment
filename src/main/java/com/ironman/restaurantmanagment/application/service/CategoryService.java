package com.ironman.restaurantmanagment.application.service;

import com.ironman.restaurantmanagment.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import com.ironman.restaurantmanagment.shared.exception.DataNotFoundException;

import java.util.List;

public interface CategoryService {
    List<CategorySmallDto> findAll();
    CategoryDto findById(Long id) throws DataNotFoundException;

//    CategoryDto create(CategoryBodyDto categoryBodyDto);
//    CategoryDto update(Long id, CategoryBodyDto categoryBodyDto);
//    CategoryDto disable(Long id);

    CategorySavedDto create(CategoryBodyDto categoryBodyDto) throws DataNotFoundException;
    CategorySavedDto update(Long id, CategoryBodyDto categoryBodyDto) throws DataNotFoundException;
    CategorySavedDto disable(Long id) throws DataNotFoundException;

    List<CategorySmallDto> findByState(String state);

    List<CategorySmallDto> fndByName(String name);

    List<CategorySmallDto> findAllByFilters(String name, String state);
}

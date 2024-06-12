package com.ironman.restaurantmanagment.application.service;

import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.persistence.entity.Category;

import java.util.List;

public interface CategoryService {
    List<CategorySmallDto> findAll();
    CategoryDto findById(Long id);
}

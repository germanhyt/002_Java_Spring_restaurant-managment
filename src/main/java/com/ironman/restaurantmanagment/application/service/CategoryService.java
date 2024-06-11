package com.ironman.restaurantmanagment.application.service;

import com.ironman.restaurantmanagment.persistence.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
}

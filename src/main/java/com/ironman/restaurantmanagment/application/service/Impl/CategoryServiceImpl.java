package com.ironman.restaurantmanagment.application.service.Impl;

import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagment.application.service.CategoryService;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import com.ironman.restaurantmanagment.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Lombok Annotation
@RequiredArgsConstructor
// Spring Stereotype Annotation
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

//    Lo generamos con Lombok Annotatons
//    public CategoryServiceImpl(CategoryRepository categoryRepository, ) {
//        this.categoryRepository = categoryRepository;
//    }

    @Override
    public List<CategorySmallDto> findAll() {
//        1. Sin Mapper
//        return (List<CategorySmallDto>) categoryRepository.findAll();
//        2. Progra imperativa
//        List<CategorySmallDto> dtos = new ArrayList<>();
//        List<Category> categories= (List<Category>) categoryRepository.findAll();
//        for(Category category: categories){
//            dtos.add(categoryMapper.toSmallDto(category));
//        }
//        return dtos;
//        3. Progra Funcional
        return ((List<Category>) categoryRepository.findAll())
                .stream()
                .map(category -> categoryMapper.toSmallDto(category))
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
//        1. Sin Mapper
//        return categoryRepository.findById(id).orElse(null);
//        2. Progra Funcional
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElse(null);
    }
}

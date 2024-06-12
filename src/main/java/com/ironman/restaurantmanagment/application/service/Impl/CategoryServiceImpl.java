package com.ironman.restaurantmanagment.application.service.Impl;

import com.ironman.restaurantmanagment.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagment.application.service.CategoryService;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import com.ironman.restaurantmanagment.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public CategorySavedDto create(CategoryBodyDto categoryBodyDto) {

        Category category = categoryMapper.toEntity(categoryBodyDto);
        category.setState("A");
        category.setCreateAt(LocalDateTime.now());

        Category saveCategory = categoryRepository.save(category);
        return categoryMapper.toSavedDto(saveCategory);
    }

    @Override
    public CategorySavedDto update(Long id, CategoryBodyDto categoryBodyDto) {

        Category category = categoryRepository.findById(id).get();
        categoryMapper.updateEntity(category, categoryBodyDto);
        category.setUpdateTime(LocalDateTime.now());

//        1.
//        Category saveCategory = categoryRepository.save(category);
//        return categoryMapper.toDto(saveCategory);
//        2
        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }

    @Override
    public CategorySavedDto disable(Long id) {

        Category category = categoryRepository.findById(id).get();
        category.setState("E");  // Eliminación Logica

        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }
}

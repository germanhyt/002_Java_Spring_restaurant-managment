package com.ironman.restaurantmanagment.application.service.Impl;

import com.ironman.restaurantmanagment.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagment.application.service.CategoryService;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import com.ironman.restaurantmanagment.persistence.repository.CategoryRepository;
import com.ironman.restaurantmanagment.shared.state.enums.State;
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

    @Override
    public List<CategorySmallDto> findAll() {

//        3. Progra Funcional
        return  categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.toSmallDto(category))
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {

//        2. Progra Funcional
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElse(null);
    }

    @Override
    public CategorySavedDto create(CategoryBodyDto categoryBodyDto) {

        Category category = categoryMapper.toEntity(categoryBodyDto);
        category.setState(State.ENABLED.getValue());
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
        category.setState(State.DISABLED.getValue());  // Eliminación Lógica

        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }

    @Override
    public List<CategorySmallDto> findByState(String state) {
       return categoryRepository.findByStateOrderByIdDesc(state)
               .stream() // Para convertir a Stream
               .map(categoryMapper::toSmallDto) // Para convertir a CategorySmallDto
               .toList();  // Para convertir a List
    }

    @Override
    public List<CategorySmallDto> fndByName(String name) {
        return categoryRepository.fndByName(name)
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }


}

package com.ironman.restaurantmanagment.application.service;

import com.ironman.restaurantmanagment.application.dto.category.*;
import com.ironman.restaurantmanagment.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagment.shared.page.PageResponse;
import org.springframework.data.domain.Page;

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

    // Paginado
    Page<CategoryDto> findAllPaginated(int page, int size);

    PageResponse<CategoryDto> paginatedSearch(CategoryFilterDto filter);

}

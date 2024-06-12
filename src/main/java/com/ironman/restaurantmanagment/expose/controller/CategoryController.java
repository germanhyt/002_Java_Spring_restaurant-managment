package com.ironman.restaurantmanagment.expose.controller;

import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.application.service.CategoryService;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import com.ironman.restaurantmanagment.persistence.repository.CategoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Para RESTAPI
@RestController
// Para DEFINIR LA RUTA PARA ACCEDER AL CONTROLADOR
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategorySmallDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }


//    private CategoryRepository categoryRepository;
//
//    public CategoryController(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    @GetMapping
//    public List<Category> findAll() {
//
//        List<Category> categories= (List<Category>) categoryRepository.findAll();
//        return categories;
//    }
//
//    @GetMapping("/{id}")
//    public Category findById(@PathVariable("id") Long id){
//        Category category= categoryRepository.findById(id).orElse(null);
//        return category;
//    }

}

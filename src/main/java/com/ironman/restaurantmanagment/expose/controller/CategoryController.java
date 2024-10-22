package com.ironman.restaurantmanagment.expose.controller;

import com.ironman.restaurantmanagment.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.application.service.CategoryService;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import com.ironman.restaurantmanagment.persistence.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//- Lombok Annotation
// Genera un constructor con todos los argumentos
@RequiredArgsConstructor
//- Spring Stereotype Annotation
// Para RESTAPI
@RestController
// Para DEFINIR LA RUTA PARA ACCEDER AL CONTROLADOR
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }

    @GetMapping
    public List<CategorySmallDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable("id") Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public CategorySavedDto create(@RequestBody CategoryBodyDto categoryBodyDto) {
        return categoryService.create(categoryBodyDto);
    }

    //    @PathVariable("id") // Para obtener el id de la URL
    @PutMapping("/{id}")
    public CategorySavedDto update(@PathVariable("id") Long id, @RequestBody CategoryBodyDto categoryBodyDto) {
        return categoryService.update(id, categoryBodyDto);
    }

    @DeleteMapping("/{id}")
    public CategorySavedDto disable(@PathVariable("id") Long id) {
        return categoryService.disable(id);
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

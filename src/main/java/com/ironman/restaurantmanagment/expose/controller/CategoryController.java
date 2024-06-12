package com.ironman.restaurantmanagment.expose.controller;

import com.ironman.restaurantmanagment.application.dto.category.CategoryBodyDto;
import com.ironman.restaurantmanagment.application.dto.category.CategoryDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySavedDto;
import com.ironman.restaurantmanagment.application.dto.category.CategorySmallDto;
import com.ironman.restaurantmanagment.application.service.CategoryService;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import com.ironman.restaurantmanagment.persistence.repository.CategoryRepository;
import com.ironman.restaurantmanagment.shared.exception.DataNotFoundException;
import jakarta.validation.Valid;
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
    public CategoryDto findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return categoryService.findById(id);
    }

    @PostMapping
    public CategorySavedDto create(@Valid @RequestBody CategoryBodyDto categoryBodyDto) throws DataNotFoundException{
        return categoryService.create(categoryBodyDto);
    }

    //    @PathVariable("id") // Para obtener el id de la URL
    @PutMapping("/{id}")
    public CategorySavedDto update(@PathVariable("id") Long id,@Valid @RequestBody CategoryBodyDto categoryBodyDto) throws DataNotFoundException  {
        return categoryService.update(id, categoryBodyDto);
    }

    @DeleteMapping("/{id}")
    public CategorySavedDto disable(@PathVariable("id") Long id) throws DataNotFoundException{
        return categoryService.disable(id);
    }

    @GetMapping("/state/{state}")
    public List<CategorySmallDto> findByState(@PathVariable("state") String state) {
        return categoryService.findByState(state);
    }

    @GetMapping("/name/{name}")
    public List<CategorySmallDto> fndByName(@PathVariable("name") String name) {
        return categoryService.fndByName(name);
    }

    @GetMapping("/filters")
    public List<CategorySmallDto> findAllByFilters(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "state", required = false) String state) {

        return categoryService.findAllByFilters(name, state);
    }



}

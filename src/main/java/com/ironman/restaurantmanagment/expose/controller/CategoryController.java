package com.ironman.restaurantmanagment.expose.controller;

import com.ironman.restaurantmanagment.application.dto.category.*;
import com.ironman.restaurantmanagment.application.service.CategoryService;
import com.ironman.restaurantmanagment.shared.constants.StatusCode;
import com.ironman.restaurantmanagment.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagment.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagment.shared.exception.model.GeneralError;
import com.ironman.restaurantmanagment.shared.page.PageResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    // Pagination
    @ApiResponse(responseCode = StatusCode.OK, description = "List of all categories paginated")
    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<CategoryDto>> findAllPaginated(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAllPaginated(page, size));
    }

    // Filter Advanced
    @ApiResponse(responseCode = StatusCode.OK, description = "List of all categories paginated")
    @GetMapping("/paginated-search")
    public ResponseEntity<PageResponse<CategoryDto>> paginatedSearch(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "createAtFrom", required = false) LocalDate createAtFrom,
            @RequestParam(value = "createAtTo", required = false) LocalDate createAtTo,
            @RequestParam(value = "sortField", required = false) String sortField,
            @RequestParam(value = "sortOrder", required = false) String sortOrder

    ) {
        CategoryFilterDto filter = CategoryFilterDto.builder()
                .page(page)
                .size(size)
                .name(name)
                .description(description)
                .state(state)
                .createAtFrom(createAtFrom)
                .createAtTo(createAtTo)
                .sortField(sortField)
                .sortOrder(sortOrder)
                .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.paginatedSearch(filter));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of all categories")
    @GetMapping
    public ResponseEntity<List<CategorySmallDto>> findAll() {// Con el ResponseEntity se puede devolver un objeto con un estado HTTP,y el nivel de maduracion de la API es 2
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Category by id")
    @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Category not Found", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GeneralError.class)
    ))
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findById(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }


    @ApiResponse(responseCode = StatusCode.CREATED, description = "Category created")
    @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Category not Found", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ArgumentNotValidError.class)
    ))
    @PostMapping
    public ResponseEntity<CategorySavedDto> create(@Valid @RequestBody CategoryBodyDto categoryBodyDto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(categoryBodyDto));
    }


    //    @PathVariable("id") // Para obtener el id de la URL
    @ApiResponse(responseCode = StatusCode.OK, description = "Category updated")
    @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Category not Found", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GeneralError.class)
    ))
    @ApiResponse(responseCode = StatusCode.BAD_REQUEST, description = "Invalid Arguments", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = ArgumentNotValidError.class)
    ))
    @PutMapping("/{id}")
    public ResponseEntity<CategorySavedDto> update(@PathVariable("id") Long id, @Valid @RequestBody CategoryBodyDto categoryBodyDto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, categoryBodyDto));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "Category deleted")
    @ApiResponse(responseCode = StatusCode.NOT_FOUND, description = "Category not Found", content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            schema = @Schema(implementation = GeneralError.class)
    ))
    @DeleteMapping("/{id}")
    public ResponseEntity<CategorySavedDto> disable(@PathVariable("id") Long id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.disable(id));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by state")
    @GetMapping("/state/{state}")
    public ResponseEntity<List<CategorySmallDto>> findByState(@PathVariable("state") String state) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findByState(state));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by name")
    @GetMapping("/name/{name}")
    public ResponseEntity<List<CategorySmallDto>> fndByName(@PathVariable("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.fndByName(name));
    }


    @ApiResponse(responseCode = StatusCode.OK, description = "List of categories by filters")
    @GetMapping("/filters")
    public ResponseEntity<List<CategorySmallDto>> findAllByFilters(@RequestParam(value = "name", required = false) String name,
                                                                   @RequestParam(value = "state", required = false) String state) {

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAllByFilters(name, state));
    }


}

package com.ironman.restaurantmanagment.application.service.Impl;

import com.ironman.restaurantmanagment.application.dto.category.*;
import com.ironman.restaurantmanagment.application.mapper.CategoryMapper;
import com.ironman.restaurantmanagment.application.service.CategoryService;
import com.ironman.restaurantmanagment.persistence.entity.Category;
import com.ironman.restaurantmanagment.persistence.enums.CategorySortField;
import com.ironman.restaurantmanagment.persistence.repository.CategoryRepository;
import com.ironman.restaurantmanagment.shared.exception.DataNotFoundException;
import com.ironman.restaurantmanagment.shared.page.PageResponse;
import com.ironman.restaurantmanagment.shared.state.enums.State;
import com.ironman.restaurantmanagment.shared.util.DateHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return categoryRepository.findAll()
                .stream()
                .map(category -> categoryMapper.toSmallDto(category))
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) throws DataNotFoundException {

//        2. Progra Funcional
        return categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> categoryGetDataNotFoundException(id));
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
    public CategorySavedDto update(Long id, CategoryBodyDto categoryBodyDto) throws DataNotFoundException {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> categoryGetDataNotFoundException(id));
        ;
        categoryMapper.updateEntity(category, categoryBodyDto);
        category.setUpdateTime(LocalDateTime.now());

//        1.
//        Category saveCategory = categoryRepository.save(category);
//        return categoryMapper.toDto(saveCategory);
//        2
        return categoryMapper.toSavedDto(categoryRepository.save(category));
    }

    @Override
    public CategorySavedDto disable(Long id) throws DataNotFoundException {

        Category category = categoryRepository.findById(id).orElseThrow(() -> categoryGetDataNotFoundException(id));
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

    @Override
    public List<CategorySmallDto> findAllByFilters(String name, String state) {
        return categoryRepository.findAllByFilters(name, state)
                .stream()
                .map(categoryMapper::toSmallDto)
                .toList();
    }

    @Override
    public Page<CategoryDto> findAllPaginated(int page, int size) {
        // Variable
        Pageable pageable = PageRequest.of(page, size);  // Intancia para la paginación

        // Process
        Page<Category> categoryPage = categoryRepository.findAll(pageable);  // Lista de categorias paginadas

        // Result
        return categoryPage.map(categoryMapper::toDto); // Mapeo de la lista de categorias paginadas
    }

    @Override
    public PageResponse<CategoryDto> paginatedSearch(CategoryFilterDto filter) {
        // Variable
        String column = CategorySortField.getSqlColumn(filter.getSortField());  // Columna de ordenamiento

        Sort.Direction direction = Sort.Direction.fromOptionalString(filter.getSortOrder())
                .orElse(Sort.Direction.DESC);  // Dirección del ordenamiento

        Sort sort = Sort.by(direction, column);  // Ordenamiento

        Pageable pageable = PageRequest.of(filter.getPage() - 1, filter.getSize(), sort);

//old
//        Pageable pageable = PageRequest.of(filter.getPage() - 1, filter.getSize());

        // Process
        Page<Category> categoryPage = categoryRepository.paginatedSearch(
                filter.getName(),
                filter.getDescription(),
                filter.getState(),
                DateHelper.LocalDateToString(filter.getCreateAtFrom()),
                DateHelper.LocalDateToString(filter.getCreateAtTo()),
                pageable
        );

        List<CategoryDto> content = // content permite mapear la lista de categorias paginadas
                categoryPage.getContent().stream()
                        .map(categoryMapper::toDto)
                        .toList();

        // Result
//        return categoryPage.map(categoryMapper::toDto);
        return PageResponse.<CategoryDto>builder() // Retornamos un objeto de tipo PageResponse con los datos de la paginación
                .content(content)
                .number(categoryPage.getNumber() + 1)
                .numberOfElements(categoryPage.getNumberOfElements())
                .size(categoryPage.getSize())
                .totalElements(categoryPage.getTotalElements())
                .totalPages(categoryPage.getTotalPages())
                .build();
    }


    private DataNotFoundException categoryGetDataNotFoundException(Long id) {
        return new DataNotFoundException("Category not found with id: " + id);
    }
}


//
//@Override
//public org.hibernate.query.Page<CategoryDto> findAllPaginated(int page, int size) {
//    // Variable
//    Pageable pageable= PageRequest.of(page,size);
//
//    // Process
//    org.hibernate.query.Page<Category> categoryPage= categoryRepository.findAll(pageable);
//
//    // Result
//    return categoryPage.map(categoryMapper::toDto);
//}
//
//@Override
//public org.hibernate.query.Page<CategoryDto> paginatedSearch(CategoryFilterDto filter) {
//    // Variable
//    Pageable pageable= PageRequest.of(filter.getPage(),filter.getSize());
//
//    // Process
//    org.hibernate.query.Page<Category> categoryPage = CategoryRepository.paginatedSearch(
//            filter.getName(),
//            filter.getDescription(),
//            filter.getState(),
//            filter.getCreateAtFrom(),
//            filter.getCreateAtTo(),
//            pageable
//    );
//
//    // Result
//    return categoryPage.map(categoryMapper::toDto);
//}

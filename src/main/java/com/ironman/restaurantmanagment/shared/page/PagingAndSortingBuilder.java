package com.ironman.restaurantmanagment.shared.page;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;

public abstract class PagingAndSortingBuilder {

    // Método para construir una respuesta paginada
    public <T, U> PageResponse<U> buildPageResponse(Page<T> page, Function<T, U> converter) {

        // Mapear los elementos de la página a una lista
        List<U> content = page.getContent()
                .stream()
                .map(converter)
                .toList();

        // Construir la respuesta paginada
        return PageResponse.<U>builder()
                .content(content)
                .number(page.getNumber() + 1)
                .numberOfElements(page.getNumberOfElements())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    // Método
    public Pageable buildPageable(PageableRequest request, String sqlColumn) {
        // Obtener la dirección de ordenamiento
        Sort.Direction direction = Sort.Direction
                .fromOptionalString(request.getSortOrder())
                .orElse(Sort.Direction.DESC);

        // Construir la instancia de Pageable
        Sort sort = Sort.by(direction, sqlColumn);

        // Retornar la instancia de Pageable
        return PageRequest.of(
                request.getPage() - 1,
                request.getSize(),
                sort
        );
    }
}
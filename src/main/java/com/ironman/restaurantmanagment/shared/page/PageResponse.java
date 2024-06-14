package com.ironman.restaurantmanagment.shared.page;

import lombok.*;

import java.util.List;

// Lombok Annotations
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    // Attributes
    private List<T> content;
    private Integer number;
    private Integer numberOfElements;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
}

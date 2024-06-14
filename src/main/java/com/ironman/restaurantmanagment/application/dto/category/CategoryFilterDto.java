package com.ironman.restaurantmanagment.application.dto.category;

import com.ironman.restaurantmanagment.shared.page.PageableRequest;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CategoryFilterDto extends PageableRequest {
    private String name;
    private String state;
    private String description;
    private LocalDate createAtFrom;
    private LocalDate createAtTo;
}

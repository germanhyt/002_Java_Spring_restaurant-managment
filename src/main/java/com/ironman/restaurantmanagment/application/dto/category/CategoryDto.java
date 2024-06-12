package com.ironman.restaurantmanagment.application.dto.category;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    // Attributes
    private long id;
    private String name;
    private String description;
    private String urlKey;
    private String state;
    private LocalDateTime createAt;
    private LocalDateTime updateTime;

}

package com.ironman.restaurantmanagment.application.dto.category;

import com.ironman.restaurantmanagment.shared.state.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategorySavedDto {
    // Attributes
    private long id;
    private State state;
}

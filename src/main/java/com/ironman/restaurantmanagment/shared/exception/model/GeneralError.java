package com.ironman.restaurantmanagment.shared.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Lombok Annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralError {

    private String message;
}

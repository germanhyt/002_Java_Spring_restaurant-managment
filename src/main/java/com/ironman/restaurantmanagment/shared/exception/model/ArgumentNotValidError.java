package com.ironman.restaurantmanagment.shared.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

//Lombok Annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ArgumentNotValidError {
    private String message;
    private Map<String, String> error;
}

package com.ironman.restaurantmanagment.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CategorySortField {

    // Enum values
    ID("id", "id"),
    NAME("name", "name"),
    DESCRIPTION("description", "description"),
    STATE("state", "state"),
    CREATE_AT("createAt", "create_at");

    // Enum fields
    private final String fieldName;
    private final String columnName;

    // Enum methods
    public static String getSqlColumn(String fieldName) {
        return Arrays.stream(CategorySortField.values()) // Retornar un Stream de los valores de la enumeración
                .filter(sortField -> sortField.getFieldName().equals(fieldName))
                .findFirst()
                .map(CategorySortField::getColumnName)
                .orElseGet(ID::getColumnName);  // Si no encuentra el campo, retorna el campo ID
    }

}

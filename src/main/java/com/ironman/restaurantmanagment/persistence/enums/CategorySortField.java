package com.ironman.restaurantmanagment.persistence.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum CategorySortField {

    ID("id", "id"),
    NAME("name", "name"),
    DESCRIPTION("description", "description"),
    STATE("state", "state"),
    CREATE_AT("createAt", "create_at");

    private final String fieldName;
    private final String columnName;

    public static String getSqlColumn(String fieldName) {
        return Arrays.stream(CategorySortField.values())
                .filter(sortField -> sortField.getFieldName().equals(fieldName))
                .findFirst()
                .map(CategorySortField::getColumnName)
                .orElseGet(ID::getColumnName);
    }

}

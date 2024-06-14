package com.ironman.restaurantmanagment.shared.state.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ironman.restaurantmanagment.shared.state.Serializer.StateSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

// Swagger annotation
@Schema(type = "object", oneOf = {State.class}, implementation = State.class)
//Lombok annotations
@Getter

// Jackson annotation
@JsonSerialize(using = StateSerializer.class)
public enum State {

    ENABLED("A", true) {
        @Override
        public String toString() {
            return "Habilitado";
        }
    },
    DISABLED("E", false) {
        @Override
        public String toString() {
            return "Deshabilitado";
        }
    };

    private final String value;
    private final String name;
    private final boolean isEnabled;

    State(String value, boolean isEnabled) {
        this.value = value;
        this.name = this.toString();
        this.isEnabled = isEnabled;
    }
}

package com.ironman.restaurantmanagment.shared.state.mapper;

import com.ironman.restaurantmanagment.shared.state.enums.State;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ValueMapping;

// MapStruct Annotation
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StateMapper {
    @ValueMapping(source = "A", target = "ENABLED")
    @ValueMapping(source = "E", target = "DISABLED")
    State toState(String value);
}

package com.ironman.restaurantmanagment.shared.page;

import lombok.*;
import lombok.experimental.SuperBuilder;

// Lombok Annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder // Notiacion de Lombok que nos permite usar el patron de diseño Builder, para crear objetos de esta clase de manera mas sencilla, sin tener que usar el constructor, nos permite usar el patron de diseño Builder, para crear objetos de esta clase de manera mas sencilla, sin tener que usar el constructor, nos permite usar el patron de diseño Builder, para crear objetos de esta clase de manera mas sencilla, sin tener que usar el constructor, es decir, podemos usar el patron de diseño Builder, para crear objetos de esta clase de manera mas sencilla, sin tener que usar el constructor, es decir podemos usar el patron de diseño Builder, para crear objetos sin tener que usar el constructor, es decir podemos usar el patron de diseño Builder, para crear objetos sin tener que usar el constructor

public abstract class PageableRequest {
    private int page;
    private int size;
    private String sortField;
    private String sortOrder;
}

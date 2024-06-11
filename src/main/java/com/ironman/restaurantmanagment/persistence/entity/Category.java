package com.ironman.restaurantmanagment.persistence.entity;

/*
* CREATE TABLE public.categories (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	description varchar(255) NULL,
	url_key varchar(255) NULL,
	state varchar(255) NOT NULL DEFAULT 'A'::bpchar,
	created_at varchar(255) NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated_at varchar(255) NULL,
* */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

// Lombok Annotations
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// JPA Annotations
@Entity
@Table(name = "categories")
public class Category implements java.io.Serializable {
    // Attributes
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @Column(name = "url_key")
    private String urlKey;
    private String state;
    @Column(name = "created_at")
    private LocalDateTime createAt;
    @Column(name = "updated_at")
    private LocalDateTime updateTime;

    // Constructor

    // Getters and Setters

}

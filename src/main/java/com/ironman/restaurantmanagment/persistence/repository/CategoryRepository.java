package com.ironman.restaurantmanagment.persistence.repository;

// Para importar la ENTITY
import com.ironman.restaurantmanagment.persistence.entity.Category;
// Para usar la implementación CRUD de Spring
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {

}

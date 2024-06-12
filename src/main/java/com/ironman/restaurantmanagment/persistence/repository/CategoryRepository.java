package com.ironman.restaurantmanagment.persistence.repository;

// Para importar la ENTITY
import com.ironman.restaurantmanagment.persistence.entity.Category;
// Para usar la implementación CRUD de Spring
import org.springframework.data.repository.CrudRepository;
// ListCrudRepository es una interfaz que extiende de CrudRepository y CrudRepository es una interfaz que extiende de Repository
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category,Long> {

}

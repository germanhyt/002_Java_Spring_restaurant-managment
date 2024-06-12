package com.ironman.restaurantmanagment.persistence.repository;

// Para importar la ENTITY
import com.ironman.restaurantmanagment.persistence.entity.Category;
// Para usar la implementación CRUD de Spring
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// ListCrudRepository es una interfaz que extiende de CrudRepository y CrudRepository es una interfaz que extiende de Repository
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends ListCrudRepository<Category,Long> {

    List<Category> findByStateOrderByIdDesc(String state);   // findBy - Population - GreaterThan

    @Query(value=" SELECT c FROM Category AS c " +
            "WHERE UPPER(c.name) " +
            "LIKE UPPER(CONCAT('%',:name,'%'))" +
            "ORDER BY c.id DESC")
    List<Category> fndByName(@Param("name") String name);
}

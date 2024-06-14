package com.ironman.restaurantmanagment.persistence.repository;

// Para importar la ENTITY

import com.ironman.restaurantmanagment.persistence.entity.Category;
// Para usar la implementación CRUD de Spring
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// ListCrudRepository es una interfaz que extiende de CrudRepository y CrudRepository es una interfaz que extiende de Repository
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends
        ListCrudRepository<Category, Long>,
        ListPagingAndSortingRepository<Category, Long> {

    List<Category> findByStateOrderByIdDesc(String state);   // findBy - Population - GreaterThan

    @Query(value = " SELECT c FROM Category AS c " +
            "WHERE UPPER(c.name) " +
            "LIKE UPPER(CONCAT('%',:name,'%'))" +
            "ORDER BY c.id DESC")
    List<Category> fndByName(@Param("name") String name);


    @Query(value = "SELECT c FROM Category AS c"
            + " WHERE ( :#{#name} IS NULL OR UPPER(c.name) LIKE UPPER(CONCAT('%',:name,'%')) )"
            + " AND ( :#{#state} IS NULL OR UPPER(c.state) = UPPER(:state) )"
    )
    List<Category> findAllByFilters(@Param("name") String name, @Param("state") String state);


    @Query(value = "SELECT "+
            "c.id, c.name, c.description, c.url_key, c.state, c.created_at, c.updated_at " +
            "FROM categories c " +
            "WHERE ( :name IS NULL OR UPPER(c.name) LIKE UPPER(CONCAT('%',:name,'%')) ) " +
            "AND ( :description IS NULL OR UPPER(c.description) LIKE UPPER(CONCAT('%',:description,'%')) ) " +
            "AND ( :state IS NULL OR UPPER(c.state) = UPPER(:state) ) "+
            "AND ( :createAtFrom IS NULL OR DATE(c.created_at) >= TO_DATE(:createAtFrom,'YYY-MM-DD') ) "+
            "AND ( :createAtTo IS NULL OR DATE(c.created_at) <= TO_DATE(:createAtTo,'YYY-MM-DD') ) ",
            nativeQuery = true
    )
    Page<Category> paginatedSearch(
            @Param("name") String name,
            @Param("description") String description,
            @Param("state") String state,
            @Param("createAtFrom") String createAtFrom,
            @Param("createAtTo") String createAtTo,
            Pageable pageable
    );
}

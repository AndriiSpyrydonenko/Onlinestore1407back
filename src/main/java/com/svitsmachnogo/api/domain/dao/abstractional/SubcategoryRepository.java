package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, String> {

    @Override
    @Query("""
            from Subcategory s 
            join fetch s.products 
            where s.id = :id
            """)
    Optional<Subcategory> findById(String id);
}

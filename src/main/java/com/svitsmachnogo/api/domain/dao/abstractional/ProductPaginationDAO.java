package com.svitsmachnogo.api.domain.dao.abstractional;

import com.svitsmachnogo.api.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductPaginationDAO  extends JpaRepository<Product, Integer> {

    @Query("select p from Product p where p.id in(:idList)")
    Page<Product> getAll(List<Integer> idList , PageRequest pageRequest);

}

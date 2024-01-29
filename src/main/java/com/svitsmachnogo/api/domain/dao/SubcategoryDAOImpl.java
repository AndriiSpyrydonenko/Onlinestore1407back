package com.svitsmachnogo.api.domain.dao;

import com.svitsmachnogo.api.domain.dao.abstractional.SubcategoryDAO;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SubcategoryDAOImpl implements SubcategoryDAO {

    private final EntityManager entityManager;

    @Override
    public List<Subcategory> findAllByCategoryId(String categoryId) {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);

        Query query = session.createQuery("from Subcategory s " +
                                          "join fetch s.products  " +
                                          "where s.category.id = :categoryId")
                .setParameter("categoryId", categoryId);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Optional<Subcategory> findById(String subcategoryId) {

        Query query = entityManager
                .createQuery("""
                        from Subcategory s 
                        join fetch s.products 
                        where s.id = :subcategoryId
                                        """)
                .setParameter("subcategoryId", subcategoryId);

        return Optional.ofNullable((Subcategory) query.getSingleResult());
    }
}

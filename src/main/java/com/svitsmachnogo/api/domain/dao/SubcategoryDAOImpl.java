package com.svitsmachnogo.api.domain.dao;

import com.svitsmachnogo.api.domain.dao.abstractional.SubcategoryDAO;
import com.svitsmachnogo.api.domain.entity.Product;
import com.svitsmachnogo.api.domain.entity.Subcategory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class SubcategoryDAOImpl implements SubcategoryDAO {

    @Autowired
    EntityManager entityManager;

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
    public Subcategory findById(String subcategoryId) {
        Session session = entityManager.unwrap(Session.class);
        session.setDefaultReadOnly(true);
        Query query = session.createQuery("from Subcategory s " +
                        "join fetch s.products  " +
                        "where s.id = :subcategoryId")
                .setParameter("subcategoryId", subcategoryId);
        return (Subcategory) query.getSingleResult();
    }
}
